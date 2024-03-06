package com.tsk.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContains(String nameProduct);
	
}
