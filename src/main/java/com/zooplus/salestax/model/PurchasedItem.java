package com.zooplus.salestax.model;

import java.text.DecimalFormat;

public class PurchasedItem {

	private int quantity;
	private String name;
	private double totalCost;
	private DecimalFormat df;

	public PurchasedItem(int quantity, String name, double totalCost, DecimalFormat df) {
		super();
		this.quantity = quantity;
		this.name = name;
		this.totalCost = totalCost;
		this.df = df;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return quantity + " " + name + " : " + df.format(totalCost);
	}

}
