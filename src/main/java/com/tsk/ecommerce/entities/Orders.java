package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
	private Date date;
	
	@NotBlank(message = "L'identité du client est obligatoire")
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	
	@OneToOne
	private Pannier pannier;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private Collection<Delivery> deliveries;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Collection<Delivery> deliveries) {
		this.deliveries = deliveries;
	}


	public Pannier getPannier() {
		return pannier;
	}


	public void setPannier(Pannier pannier) {
		this.pannier = pannier;
	}
	
	

	

}
