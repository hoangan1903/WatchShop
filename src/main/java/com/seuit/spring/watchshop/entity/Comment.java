package com.seuit.spring.watchshop.entity;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="comment")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "createAt",allowGetters = true)
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comment")
	private Integer id;
	
	@Column(name="content",nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_product_detail")
	@JsonIgnore
	private ProductDetail productDetail;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id_customer")
	private Customer customer;
	
	@JoinColumn(name="create_at",nullable = false,updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public Customer getCustomer() {
		return customer;
	}

	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreateAt() {
		return createAt;
	}
	 

	
	
	
	public Comment(Integer id, String content, ProductDetail productDetail, Customer customer, Date createAt) {
		super();
		this.id = id;
		this.content = content;
		this.productDetail = productDetail;
		this.customer = customer;
		this.createAt = createAt;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", productDetail=" + productDetail + ", customer="
				+ customer + ", createAt=" + createAt + "]";
	}

	
	
	
}
