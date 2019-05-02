package com.seuit.spring.watchshop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="banner")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "createAt",allowGetters = true)
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_banner")
	private Integer id;
	
	@Column(name="url",nullable = false)
	private String url;
	
	@Column(name="description",nullable = true)
	private String description;
	
	@Column(name="create_at",updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public Banner(Integer id, String url, String description) {
		super();
		this.id = id;
		this.url = url;
		this.description = description;
	}

	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", url=" + url + ", description=" + description + ", createAt=" + createAt + "]";
	}
	
	
	
}
