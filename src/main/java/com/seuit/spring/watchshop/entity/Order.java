package com.seuit.spring.watchshop.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "createAt",allowGetters = true)
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_order")
	private Integer id;
	
	@Column(name="price")
	private Double price;
	
	@Column(nullable = false,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createAt;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id_order_status")
	private OrderStatus orderStatusO;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id_customer")
	private Customer customerO;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id_payment")
	private Payment paymentO;
	
	@OneToMany(mappedBy = "orderO",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<OrderDetail> OrderDetails = new HashSet<OrderDetail>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public OrderStatus getOrderStatusO() {
		return orderStatusO;
	}

	public void setOrderStatusO(OrderStatus orderStatusO) {
		this.orderStatusO = orderStatusO;
	}

	public Customer getCustomerO() {
		return customerO;
	}

	public void setCustomerO(Customer customerO) {
		this.customerO = customerO;
	}

	public Payment getPaymentO() {
		return paymentO;
	}

	public void setPaymentO(Payment paymentO) {
		this.paymentO = paymentO;
	}

	@JsonIgnore
	public Set<OrderDetail> getOrderDetails() {
		return OrderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		OrderDetails = orderDetails;
	}

	public Date getCreateAt() {
		return createAt;
	}
	public Integer getOrderStatusId() {
		return this.orderStatusO.getId();
	}
	public Integer getOrderPaymentId() {
		return this.paymentO.getId();
	}
}
