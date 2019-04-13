package com.seuit.spring.watchshop.entity;

public class CustomerAPI {
	private User user;
	private Customer customer;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CustomerAPI() {
		super();
	}
	@Override
	public String toString() {
		return "CustomerAPI [user=" + user + ", customer=" + customer + "]";
	}
	
}
