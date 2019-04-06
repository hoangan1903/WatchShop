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
@Table(name="firm")
public class Firm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_firm")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="firm",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Product> products;

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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Firm(String name) {
		super();
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
