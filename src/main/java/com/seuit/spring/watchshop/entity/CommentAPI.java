package com.seuit.spring.watchshop.entity;

import java.util.Date;

public class CommentAPI{
	private Comment comment;
	private Integer idProductDetail;
	private Integer idCustomer;
	public Integer getIdProductDetail() {
		return idProductDetail;
	}
	public void setIdProductDetail(Integer idProductDetail) {
		this.idProductDetail = idProductDetail;
	}
	public Integer getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public CommentAPI(Comment comment, Integer idProductDetail, Integer idCustomer) {
		super();
		this.comment = comment;
		this.idProductDetail = idProductDetail;
		this.idCustomer = idCustomer;
	}
	public CommentAPI() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CommentAPI [comment=" + comment + ", idProductDetail=" + idProductDetail + ", idCustomer=" + idCustomer
				+ "]";
	}
	
	
	
	
}
