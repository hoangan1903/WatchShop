package com.seuit.spring.watchshop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")

public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_product")
	private Integer id;
	
	@NotBlank(message = "Empty")
	@Column(name="code_name")
	private String codeName;
	
	@NotBlank
	@Column(name="image")
	private String image;
	
	@NotNull
	@Min(1)
	@Column(name="price")
	private Double price;
	
	@NotNull
	@Column(name="available")
	private Integer available;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_firm")
	private Firm firm;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="id_product_detail")
	@JsonIgnore
	private ProductDetail productDetail;
	
	
	@OneToMany(mappedBy = "productC",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<CartDetail> CartDetails = new HashSet<CartDetail>();
	
	@OneToMany(mappedBy = "productO",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<OrderDetail> OrderDetails =  new HashSet<OrderDetail>();
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Firm getFirm() {
		return firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	
	
	@JsonIgnore
	public Set<CartDetail> getCartDetails() {
		return CartDetails;
	}

	public void setCartDetails(Set<CartDetail> cartDetails) {
		CartDetails = cartDetails;
	}
	
	@JsonIgnore
	public Set<OrderDetail> getOrderDetails() {
		return OrderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		OrderDetails = orderDetails;
	}

	public Product(String codeName, String image, Double price, Integer available) {
		super();
		this.codeName = codeName;
		this.image = image;
		this.price = price;
		this.available = available;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", codeName=" + codeName + ", image=" + image + ", price=" + price + ", available="
				+ available + ", firm=" + firm + ", productDetail=" + productDetail + ", CartDetails=" + CartDetails
				+ ", OrderDetails=" + OrderDetails + "]";
	}
}
