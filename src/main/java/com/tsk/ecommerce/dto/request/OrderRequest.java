package com.tsk.ecommerce.dto.request;

import java.util.Collection;

import javax.validation.constraints.NotBlank;

import com.tsk.ecommerce.entities.Customer;


public class OrderRequest {
	
	
	/*****	 Customer information 	******/
//	private String firstName;
//	private String lastName;
//	@Email
//	@NotBlank(message = "email ne doit pas être vide")
//	private String email;
//	private String phone;
//
//	/****	address	***/
//	@NotBlank(message = "Ce champs ne doit pas être vide")
//	private String lot;
//	private String addrPlus;
	
	
	
//	@NotBlank(message = "obligatoire")
//	@NotEmpty
//	private String city;
//	
	/***	Orderline	****/
	
	@NotBlank
	private Collection<OrderlineRequest> orderlines;
	
	@NotBlank
	private Customer customer;

	
	public OrderRequest() {
		super();
	}


	public OrderRequest(@NotBlank Collection<OrderlineRequest> orderlines, @NotBlank Customer customer) {
		super();
		this.orderlines = orderlines;
		this.customer = customer;
	}


	public Collection<OrderlineRequest> getOrderlines() {
		return orderlines;
	}


	public void setOrderlineRequests(Collection<OrderlineRequest> orderlines) {
		this.orderlines = orderlines;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
