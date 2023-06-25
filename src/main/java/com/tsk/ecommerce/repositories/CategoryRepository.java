package com.tsk.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("Select c.products from Category c where c.categoryId = ?1")
	Page<Product> findAllProductsByCategory(Integer idCategory, Pageable pageable);
	
}
