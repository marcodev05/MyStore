package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
	
}
