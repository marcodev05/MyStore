package com.tsk.ecommerce.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsk.ecommerce.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByEmail(String email);
	
	public Boolean existsByEmail(String email);
	
//	@Query("SELECT COUNT(c) from Customer c WHERE c.email = ?1 ")
//	public Integer getCountEmail(String email);
}
