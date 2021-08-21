package com.tsk.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsk.ecommerce.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
}
