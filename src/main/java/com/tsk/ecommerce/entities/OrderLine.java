package com.tsk.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class OrderLine implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrderLine;
	private Integer quantity;
	private Double subTotal;
	
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "id_order")
	private Orders order;

	public OrderLine() {
		super();
	}


	public OrderLine(Integer quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}


	public Double getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}


	public Long getIdOrderLine() {
		return idOrderLine;
	}

	public void setIdOrderLine(Long idOrderLine) {
		this.idOrderLine = idOrderLine;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "OrderLine [idOrderLine=" + idOrderLine + ", quantity=" + quantity + ", total=" + subTotal + ", product="
				+ product + "]";
	}
	
	
	
}
