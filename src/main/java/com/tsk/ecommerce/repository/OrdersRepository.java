package com.tsk.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
