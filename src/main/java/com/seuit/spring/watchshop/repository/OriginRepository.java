package com.seuit.spring.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seuit.spring.watchshop.entity.Origin;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer> {

}
