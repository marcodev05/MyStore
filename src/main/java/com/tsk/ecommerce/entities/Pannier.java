package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pannier implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPannier;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pannier")
	private Collection<OrderLine> orderLines;
	
	@OneToOne
	private Orders orders;

	public Pannier() {
		super();
	}

	public Long getIdPannier() {
		return idPannier;
	}

	public void setIdPannier(Long idPannier) {
		this.idPannier = idPannier;
	}

	public Collection<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Collection<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	
	
	
}
