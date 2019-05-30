package com.seuit.spring.watchshop.entity;

public class CommentAPI{
	private String content;
	private Integer idProduct;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public CommentAPI(String content, Integer idProduct) {
		super();
		this.content = content;
		this.idProduct = idProduct;
	}
	public CommentAPI() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CommentAPI [content=" + content + ", idProduct=" + idProduct + "]";
	}
	
	
}
