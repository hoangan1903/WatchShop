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
@Table(name="alert")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"createAt"},allowGetters = true)
public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_alert")
	private Integer id;
	
	@Column(name="content",nullable = false)
	private String content;
	
	@Column(name="create_at",nullable = false,updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date createAt;
	
	@Column(name="status",nullable = false,columnDefinition = "int default 0")
	private Integer status;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="id_user")
	private User user;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public Alert(Integer id, String content, Date createAt, Integer status, User user) {
		super();
		this.id = id;
		this.content = content;
		this.createAt = createAt;
		this.status = status;
		this.user = user;
	}

	public Alert() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", content=" + content + ", createAt=" + createAt + ", status=" + status + ", user="
				+ user + "]";
	}
	
	
	
}
