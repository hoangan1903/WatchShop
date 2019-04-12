package com.seuit.spring.watchshop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class ProductApi {
	private Product product = new Product();
	private ProductDetail productDetail = new ProductDetail();
	private Set<Image> images = new HashSet<Image>();
	
	@NotNull
	private Integer idFirm;
	@NotNull
	private Integer idOrigin;
	@NotNull
	private Integer idModel;
	
	
	
	
	public Integer getIdFirm() {
		return idFirm;
	}
	public void setIdFirm(Integer idFirm) {
		this.idFirm = idFirm;
	}
	public Integer getIdOrigin() {
		return idOrigin;
	}
	public void setIdOrigin(Integer idOrigin) {
		this.idOrigin = idOrigin;
	}
	public Integer getIdModel() {
		return idModel;
	}
	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	public ProductApi(Product product, ProductDetail productDetail, Set<Image> images, @NotNull Integer idFirm,
			@NotNull Integer idOrigin, @NotNull Integer idModel) {
		super();
		this.product = product;
		this.productDetail = productDetail;
		this.images = images;
		this.idFirm = idFirm;
		this.idOrigin = idOrigin;
		this.idModel = idModel;
	}
	public ProductApi() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProductApi [product=" + product + ", productDetail=" + productDetail + ", images=" + images
				+ ", idFirm=" + idFirm + ", idOrigin=" + idOrigin + ", idModel=" + idModel + "]";
	}
	
	
	
	
}
