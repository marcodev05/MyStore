package com.tsk.ecommerce.payload;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import com.tsk.ecommerce.entities.OrderLine;



public class OrderRequest {
	
	
	/*****	 Customer information 	******/
	private String firstName;
	private String lastName;
	@Email
	@NotBlank(message = "email ne doit pas être vide")
	private String email;
	private String phone;

	/****	address	***/
	@NotBlank(message = "Ce champs ne doit pas être vide")
	private String lot;
	private String addrPlus;
	
	@NotBlank(message = "obligatoire")
	@NotEmpty
	private String city;
	
	/***	Orderline	****/
	
	private List<OrderlineRequest> orderlineRequests;

	
	public OrderRequest() {
		super();
	}



	public OrderRequest(String firstName, String lastName,
			@Email @NotBlank(message = "email ne doit pas être vide") String email, String phone,
			@NotBlank(message = "Ce champs ne doit pas être vide") String lot, String addrPlus,
			@NotBlank(message = "Ce champs est obligatoire")String city,
			List<OrderlineRequest> orderlineRequests) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.lot = lot;
		this.addrPlus = addrPlus;
		this.city = city;
		this.orderlineRequests = orderlineRequests;
	}




	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getLot() {
		return lot;
	}


	public void setLot(String lot) {
		this.lot = lot;
	}


	public String getAddrPlus() {
		return addrPlus;
	}


	public void setAddrPlus(String addrPlus) {
		this.addrPlus = addrPlus;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}



	public List<OrderlineRequest> getOrderlineRequests() {
		return orderlineRequests;
	}



	public void setOrderlineRequests(List<OrderlineRequest> orderlineRequests) {
		this.orderlineRequests = orderlineRequests;
	}



	
	
	
	
	
	
}
