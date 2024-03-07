package com.tsk.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	List<Product> findByNameContains(String nameProduct);
}
