package com.seuit.spring.watchshop.entity;

public class Report {
	private String name;
	private long amount;
	private double price;
	private long paid;
	private double paidPrice;
	private long unpaid;
	private double unpaidPrice;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public long getPaid() {
		return paid;
	}
	public void setPaid(long paid) {
		this.paid = paid;
	}
	public double getPaidPrice() {
		return paidPrice;
	}
	public void setPaidPrice(double paidPrice) {
		this.paidPrice = paidPrice;
	}
	public long getUnpaid() {
		return unpaid;
	}
	public void setUnpaid(long unpaid) {
		this.unpaid = unpaid;
	}
	public double getUnpaidPrice() {
		return unpaidPrice;
	}
	public void setUnpaidPrice(double unpaidPrice) {
		this.unpaidPrice = unpaidPrice;
	}
	public void setUnpaidPrice(Long unpaidPrice) {
		this.unpaidPrice = unpaidPrice;
	}
	@Override
	public String toString() {
		return "Report [name=" + name + ", amount=" + amount + ", price=" + price + ", paid=" + paid + ", paidPrice="
				+ paidPrice + ", unpaid=" + unpaid + ", unpaidPrice=" + unpaidPrice + "]";
	}
	
	
	public Report() {
		super();
	}
	public Report(String name, long amount, double price) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
	}
	
	public Report(String name, long amount, double price, long paid, double paidPrice) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.paid = paid;
		this.paidPrice = paidPrice;
	}
	
	public Report(String name, long amount, double price, long paid, double paidPrice, long unpaid,
			double unpaidPrice) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.paid = paid;
		this.paidPrice = paidPrice;
		this.unpaid = unpaid;
		this.unpaidPrice = unpaidPrice;
	}
	public Report(long amount, double price) {
		super();
		this.amount = amount;
		this.price = price;
	}
	
}
