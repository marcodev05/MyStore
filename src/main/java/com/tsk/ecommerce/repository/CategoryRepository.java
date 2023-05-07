package com.tsk.ecommerce.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("Select c.products from Category c where c.categoryId = ?1")
	public Collection<Product> findAllProductsByCategory(Integer idCategory);
	
}
