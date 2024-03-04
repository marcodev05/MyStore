package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.FeatureValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureValueRepository extends JpaRepository<FeatureValue, Long> {
	
}
