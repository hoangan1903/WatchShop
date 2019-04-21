package com.seuit.spring.watchshop.entity;

import javax.validation.constraints.NotNull;

public class CartAPI {
	@NotNull
	private Integer idProduct;
	
	private Integer amount;
	
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
	public CartAPI( Integer idProduct, Integer amount) {
		super();
		this.idProduct = idProduct;
		this.amount = amount;
	}
	public CartAPI() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartAPI [idProduct=" + idProduct + ", amount=" + amount + "]";
	}
	
	
	
	
}
