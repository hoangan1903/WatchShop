package com.seuit.spring.watchshop.entity;

import javax.validation.constraints.NotNull;

public class CartAPI {
	@NotNull
	private Integer idCustomer;
	@NotNull
	private Integer idProduct;
	
	private Integer amount;
	public Integer getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public CartAPI(Integer idCustomer, Integer idProduct, Integer amount) {
		super();
		this.idCustomer = idCustomer;
		this.idProduct = idProduct;
		this.amount = amount;
	}
	public CartAPI() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartAPI [idCustomer=" + idCustomer + ", idProduct=" + idProduct + ", amount=" + amount + "]";
	}
	
	
}
