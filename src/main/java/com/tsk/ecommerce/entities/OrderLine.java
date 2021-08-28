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
	private Double total;
	
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pannier")
	private Pannier pannier;

	public OrderLine() {
		super();
	}






	public OrderLine(Integer quantity, Product product, Pannier pannier) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.pannier = pannier;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Pannier getPannier() {
		return pannier;
	}

	public void setPannier(Pannier pannier) {
		this.pannier = pannier;
	}


	@Override
	public String toString() {
		return "OrderLine [idOrderLine=" + idOrderLine + ", quantity=" + quantity + ", total=" + total + ", product="
				+ product + "]";
	}
	
	
	


	
}
