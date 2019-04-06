package com.seuit.spring.watchshop.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductApi {
	private Product product;
	private ProductDetail productDetail;
	
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
	
	
	public ProductApi(Product product, ProductDetail productDetail, Integer idFirm, Integer idOrigin, Integer idModel) {
		super();
		this.product = product;
		this.productDetail = productDetail;
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
		return "ProductApi [product=" + product + ", productDetail=" + productDetail + ", idFirm=" + idFirm
				+ ", idOrigin=" + idOrigin + ", idModel=" + idModel + "]";
	}
	
	
	
}
