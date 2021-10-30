package com.zooplus.salestax.model;

public class Item {
	private int quantity;
	private String name;
	private double shelfPrice;
	private boolean isImported;
	private boolean isExempted;

	public Item(int quantity, String name, double shelfPrice, boolean isImported, boolean isExempted) {
		super();
		this.quantity = quantity;
		this.name = name;
		this.shelfPrice = shelfPrice;
		this.isImported = isImported;
		this.isExempted = isExempted;
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

	public double getShelfPrice() {
		return shelfPrice;
	}

	public void setShelfPrice(double shelfPrice) {
		this.shelfPrice = shelfPrice;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public boolean isExempted() {
		return isExempted;
	}

	public void setExempted(boolean isExempted) {
		this.isExempted = isExempted;
	}

	@Override
	public String toString() {
		return "Item [quantity=" + quantity + ", name=" + name + ", shelfPrice=" + shelfPrice + ", isImported="
				+ isImported + ", isExempted=" + isExempted + "]";
	}

}
