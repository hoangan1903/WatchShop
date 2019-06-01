package com.seuit.spring.watchshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seuit.spring.watchshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByCodeName(String codeName);
}
