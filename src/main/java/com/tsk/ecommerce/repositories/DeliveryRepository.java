package com.tsk.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
