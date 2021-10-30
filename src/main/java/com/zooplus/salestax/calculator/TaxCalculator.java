package com.zooplus.salestax.calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zooplus.salestax.model.BillAmount;
import com.zooplus.salestax.model.Item;
import com.zooplus.salestax.model.PurchasedItem;
import com.zooplus.salestax.utils.Constants;

public class TaxCalculator {

	private Properties properties;

	private DecimalFormat df;
	private int roundOffFraction;

	public TaxCalculator(Properties properties) {
		this.properties = properties;
		df = new DecimalFormat(properties.getProperty(Constants.DECIMAL_FORMAT));
		roundOffFraction = Integer.parseInt(properties.getProperty(Constants.ROUND_OFF_FRACTION));
	}

	public List<PurchasedItem> calculateTax(List<Item> itemList, BillAmount billAmount) {
		List<PurchasedItem> checkoutItems = new ArrayList<>();
		calculateCosts(itemList, checkoutItems, billAmount);
		return checkoutItems;
	}

	private void calculateCosts(List<Item> itemList, List<PurchasedItem> checkoutItems, BillAmount billAmount) {
		double importedTaxRate = Double.parseDouble(properties.getProperty(Constants.IMPORTED_SALES_TAX));
		double basicSalesTax = Double.parseDouble(properties.getProperty(Constants.BASIC_SALES_TAX));
		itemList.stream().forEach(e -> {
			if (e.isExempted() && e.isImported()) {
				double cost = (e.getShelfPrice() * e.getQuantity());
				double taxes = roundOffValues(cost * importedTaxRate / 100);
				double totalCost = (cost + taxes);
				checkoutItems.add(new PurchasedItem(e.getQuantity(), e.getName(), totalCost, df));
				billAmount.setTotal((billAmount.getTotal() + totalCost));
				billAmount.setSalesTaxes((billAmount.getSalesTaxes() + taxes));
			} else if (e.isExempted()) {
				double cost = (e.getShelfPrice() * e.getQuantity());
				checkoutItems.add(new PurchasedItem(e.getQuantity(), e.getName(), cost, df));
				billAmount.setTotal((billAmount.getTotal() + cost));
			} else if (e.isImported()) {
				double cost = (e.getShelfPrice() * e.getQuantity());
				double taxes = roundOffValues(cost * (importedTaxRate + basicSalesTax) / 100);
				double totalCost = (cost + taxes);
				checkoutItems.add(new PurchasedItem(e.getQuantity(), e.getName(), totalCost, df));
				billAmount.setTotal((billAmount.getTotal() + totalCost));
				billAmount.setSalesTaxes((billAmount.getSalesTaxes() + taxes));
			} else {
				double cost = (e.getShelfPrice() * e.getQuantity());
				double taxes = roundOffValues(cost * basicSalesTax / 100);
				double totalCost = (cost + taxes);
				checkoutItems.add(new PurchasedItem(e.getQuantity(), e.getName(), totalCost, df));
				billAmount.setTotal((billAmount.getTotal() + totalCost));
				billAmount.setSalesTaxes((billAmount.getSalesTaxes() + taxes));
			}

		});
	}

	private double roundOffValues(double cost) {
		return ((double) Math.round(cost * roundOffFraction) / roundOffFraction);
	}

	/*
	 * private double formatValues(double amount) { return
	 * Double.parseDouble(df.format(amount)); }
	 */

}
