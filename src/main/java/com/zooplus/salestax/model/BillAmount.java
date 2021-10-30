package com.zooplus.salestax.model;

import java.text.DecimalFormat;

public class BillAmount {

	private double salesTaxes;
	private double total;
	private DecimalFormat df;

	public BillAmount(double salesTaxes, double total, String df) {
		super();
		this.salesTaxes = salesTaxes;
		this.total = total;
		this.df = new DecimalFormat(df);
	}

	public double getSalesTaxes() {
		return salesTaxes;
	}

	public void setSalesTaxes(double salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Sales Taxes=" + df.format(salesTaxes) + "\nTotal=" + df.format(total);
	}

}
