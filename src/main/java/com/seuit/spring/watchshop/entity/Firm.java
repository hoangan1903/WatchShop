package com.seuit.spring.watchshop.entity;

import java.util.List;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="firm")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Firm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_firm")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="firm",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Product> products;

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Firm(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Firm() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Firm [id=" + id + ", name=" + name + ", products=" + products + "]";
	}

	

		
}
