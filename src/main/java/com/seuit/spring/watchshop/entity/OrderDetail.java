package com.seuit.spring.watchshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_order")
	private Order orderO;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product productO;
	
	@JoinColumn(name="amount")
	private Integer amount;

	public Order getOrderO() {
		return orderO;
	}

	public void setOrderO(Order orderO) {
		this.orderO = orderO;
	}

	public Product getProductO() {
		return productO;
	}

	public void setProductO(Product productO) {
		this.productO = productO;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public OrderDetail(Order orderO, Product productO, Integer amount) {
		super();
		this.orderO = orderO;
		this.productO = productO;
		this.amount = amount;
	}

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderDetail [orderO=" + orderO + ", productO=" + productO + ", amount=" + amount + "]";
	}

	
}
