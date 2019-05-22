package com.seuit.spring.watchshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cart_detail")
public class CartDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_cart")
	private Cart cart;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product productC;
	
	@JoinColumn(name="amount")
	private Integer amount;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return productC;
	}

	public void setProduct(Product product) {
		this.productC = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Double getSubtotal() {
		return this.amount*this.productC.getPrice();
	}

	public CartDetail(Cart cart, Product product, Integer amount) {
		super();
		this.cart = cart;
		this.productC = product;
		this.amount = amount;
	}

	public CartDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartDetail [cart=" + cart + ", product=" + productC + ", amount=" + amount + "]";
	}
	
	
	
	
}
