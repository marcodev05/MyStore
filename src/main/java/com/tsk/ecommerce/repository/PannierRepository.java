package com.tsk.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Pannier;

public interface PannierRepository extends JpaRepository<Pannier, Long> {

	@Query("select p.orderLines from Pannier p where p.idPannier = ?1")
	public List<OrderLine> findOrderLineInPannier(Long idPannier);
}
