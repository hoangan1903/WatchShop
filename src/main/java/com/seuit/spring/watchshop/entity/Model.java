package com.seuit.spring.watchshop.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="model")
public class Model {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_model")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="model",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<ProductDetail> productDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Set<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}

	public Model(String name) {
		super();
		this.name = name;
	}

	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + ", productDetails=" + productDetails + "]";
	}
	
	
}
