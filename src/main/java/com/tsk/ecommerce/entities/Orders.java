package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Orders implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	private String description;
	private Double total;
	private Date createdAt;
	private Double costDelivery;
	private Boolean payed;
	private Boolean delivered;
	
	@NotBlank(message = "L'identité du client est obligatoire")
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pannier pannier;
	

	public Orders() {
		super();
	}

	
	public Orders(String description, Customer customer, Pannier pannier) {
		super();
		this.description = description;
		this.customer = customer;
		this.pannier = pannier;
	}



	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Pannier getPannier() {
		return pannier;
	}


	public void setPannier(Pannier pannier) {
		this.pannier = pannier;
	}


	public Boolean getPayed() {
		return payed;
	}


	public void setPayed(Boolean payed) {
		this.payed = payed;
	}


	public Boolean getDelivered() {
		return delivered;
	}


	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}


	public Double getCostDelivery() {
		return costDelivery;
	}


	public void setCostDelivery(Double costDelivery) {
		this.costDelivery = costDelivery;
	}


	@Override
	public String toString() {
		return "Orders [idOrder=" + idOrder + ", description=" + description + ", total=" + total + ", createdAt="
				+ createdAt + ", costDelivery=" + costDelivery + ", payed=" + payed + ", delivered=" + delivered
				+ ", customer=" + customer + ", pannier=" + pannier + "]";
	}
	
	
	
	

	

}
