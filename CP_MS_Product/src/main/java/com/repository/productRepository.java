package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Product;

@Repository
public interface productRepository extends JpaRepository<Product , Integer>{
	Product findByPid(int pid);
}
