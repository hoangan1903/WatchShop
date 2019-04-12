package com.seuit.spring.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seuit.spring.watchshop.entity.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
