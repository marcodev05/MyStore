package com.tsk.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
	
}
