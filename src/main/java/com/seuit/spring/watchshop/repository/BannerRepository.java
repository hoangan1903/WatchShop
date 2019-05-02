package com.seuit.spring.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seuit.spring.watchshop.entity.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

}
