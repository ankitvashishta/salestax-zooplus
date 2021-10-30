package com.zooplus.salestax.calculator;

import static com.zooplus.salestax.utils.Constants.BASIC_SALES_TAX;
import static com.zooplus.salestax.utils.Constants.DECIMAL_FORMAT;
import static com.zooplus.salestax.utils.Constants.IMPORTED_SALES_TAX;
import static com.zooplus.salestax.utils.Constants.ROUND_OFF_FRACTION;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zooplus.salestax.model.BillAmount;
import com.zooplus.salestax.model.Item;
import com.zooplus.salestax.model.PurchasedItem;

public class TaxCalculator {

	private Properties properties;

	private DecimalFormat df;
	private int roundOffFraction;

	public TaxCalculator(Properties properties) {
		this.properties = properties;
		df = new DecimalFormat(properties.getProperty(DECIMAL_FORMAT));
		roundOffFraction = Integer.parseInt(properties.getProperty(ROUND_OFF_FRACTION));
	}

	public List<PurchasedItem> calculateTax(List<Item> itemList, BillAmount billAmount) {
		List<PurchasedItem> checkoutItems = new ArrayList<>();
		calculateCosts(itemList, checkoutItems, billAmount);
		return checkoutItems;
	}

	private void calculateCosts(List<Item> itemList, List<PurchasedItem> checkoutItems, BillAmount billAmount) {
		double importedTaxRate = Double.parseDouble(properties.getProperty(IMPORTED_SALES_TAX));
		double basicSalesTax = Double.parseDouble(properties.getProperty(BASIC_SALES_TAX));
		itemList.stream().forEach(e -> {
			if (e.isExempted() && e.isImported()) {
				calculateItemCost(e, importedTaxRate, 0, billAmount, checkoutItems);
			} else if (e.isExempted()) {
				calculateItemCost(e, 0, 0, billAmount, checkoutItems);
			} else if (e.isImported()) {
				calculateItemCost(e, importedTaxRate, basicSalesTax, billAmount, checkoutItems);
			} else {
				calculateItemCost(e, 0, basicSalesTax, billAmount, checkoutItems);
			}
		});
	}

	private double roundOffValues(double cost) {
		return ((double) Math.round(cost * roundOffFraction) / roundOffFraction);
	}

	private void calculateItemCost(Item e, double importedTaxRate, double basicSalesTax, BillAmount billAmount,
			List<PurchasedItem> checkoutItems) {
		double cost = (e.getShelfPrice() * e.getQuantity());
		double taxes = 0.0;
		if (importedTaxRate != 0 || basicSalesTax != 0)
			taxes = roundOffValues(cost * (importedTaxRate + basicSalesTax) / 100);
		double totalCost = (cost + taxes);
		checkoutItems.add(new PurchasedItem(e.getQuantity(), e.getName(), totalCost, df));
		billAmount.setTotal((billAmount.getTotal() + totalCost));
		billAmount.setSalesTaxes((billAmount.getSalesTaxes() + taxes));
	}

}
