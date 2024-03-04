package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubProductRepository extends JpaRepository<SubProduct, Long> {
	
}
