package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	
	@OneToMany(mappedBy = "pannier" , fetch = FetchType.LAZY)
	private Collection<OrderLine> orderLines;
	
	@JsonIgnore
	@OneToOne
	private Orders orders;
	
	private Double subtotal;

	
	public Pannier() {
		super();
	}

	public Pannier(Collection<OrderLine> orderLines) {
		super();
		this.orderLines = orderLines;
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

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	
	
	
}
