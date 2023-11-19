package com.tsk.ecommerce.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
