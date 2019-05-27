package com.seuit.spring.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seuit.spring.watchshop.entity.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	PasswordResetToken findByToken(String token);
}
