package com.seuit.spring.watchshop.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product_detail")

public class ProductDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_product_detail")
	private Integer id;
	
	@NotNull
	@Column(name="size")
	private Integer size;
	
	@NotBlank
	@Column(name="case_material")
	private String caseMaterial;
	
	@NotBlank
	@Column(name="chain_material")
	private String chainMaterial;
	
	@NotBlank
	@Column(name="glass_material")
	private String glassMaterial;
	
	@NotNull
	@Column(name="water_resistance")
	private Integer waterResistance;
	
	@NotBlank
	@Column(name="other_function")
	private String otherFunction;
	
	@NotNull
	@Column(name="insurance")
	private Integer insurance;
	
	@OneToOne(mappedBy="productDetail",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Product product;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_origin")
	private Origin origin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_model")
	private Model model;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "productDetail")
	private Set<Image> images;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "productDetail",cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getCaseMaterial() {
		return caseMaterial;
	}

	public void setCaseMaterial(String caseMaterial) {
		this.caseMaterial = caseMaterial;
	}

	public String getChainMaterial() {
		return chainMaterial;
	}

	public void setChainMaterial(String chainMaterial) {
		this.chainMaterial = chainMaterial;
	}

	public String getGlassMaterial() {
		return glassMaterial;
	}

	public void setGlassMaterial(String glassMaterial) {
		this.glassMaterial = glassMaterial;
	}

	public Integer getWaterResistance() {
		return waterResistance;
	}

	public void setWaterResistance(Integer waterResistance) {
		this.waterResistance = waterResistance;
	}

	public String getOtherFunction() {
		return otherFunction;
	}

	public void setOtherFunction(String otherFunction) {
		this.otherFunction = otherFunction;
	}

	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public ProductDetail(Integer size, String caseMaterial, String chainMaterial, String glassMaterial,
			Integer waterResistance, String otherFunction, Integer insurance) {
		super();
		this.size = size;
		this.caseMaterial = caseMaterial;
		this.chainMaterial = chainMaterial;
		this.glassMaterial = glassMaterial;
		this.waterResistance = waterResistance;
		this.otherFunction = otherFunction;
		this.insurance = insurance;
	}

	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", size=" + size + ", caseMaterial=" + caseMaterial + ", chainMaterial="
				+ chainMaterial + ", glassMaterial=" + glassMaterial + ", waterResistance=" + waterResistance
				+ ", otherFunction=" + otherFunction + ", insurance=" + insurance + ", product=" + product + ", origin="
				+ origin + ", model=" + model + ", images=" + images + ", comments=" + comments + "]";
	}

	

	
	
	
}
