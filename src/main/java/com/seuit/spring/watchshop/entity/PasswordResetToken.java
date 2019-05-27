package com.seuit.spring.watchshop.entity;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PasswordResetToken {
  
    private static final int EXPIRATION = 60 * 24;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String token;
  
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
  
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate() {
		Instant instanDate = new Date().toInstant().plus(Duration.ofMinutes(this.EXPIRATION));
		this.expiryDate = Date.from(instanDate);
	}

	public PasswordResetToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
		this.setExpiryDate();
	}

	public PasswordResetToken() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
