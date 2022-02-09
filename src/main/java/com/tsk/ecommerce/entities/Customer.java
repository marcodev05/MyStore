package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCustomer;
	
	@NotBlank(message = "Votre nom est obligatoire")
	private String firstName;
	
	private String lastName;
	
	@Email
	@NotBlank(message = "email ne doit pas être vide")
	private String email;
	
	private String phone;
	
	@NotBlank(message = "Ce champs ne doit pas être vide")
	private String addr1;
	
	
	private String addr2;
	
	@NotBlank(message = "Ce champs est obligatoire")
	@NotNull
	private String city;
	
	

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private Collection<Orders> orders;

	public Customer() {
		super();
	}


	public Customer(@NotBlank(message = "Votre nom est obligatoire") String firstName, String lastName,
			@Email @NotBlank(message = "email ne doit pas être vide") String email, String phone,
			@NotBlank(message = "Ce champs ne doit pas être vide") String addr1, String addr2,
			@NotBlank(message = "Ce champs est obligatoire") @NotNull String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.city = city;
	}


	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
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

	public Collection<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}


	public String getAddr1() {
		return addr1;
	}


	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}


	public String getAddr2() {
		return addr2;
	}


	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	


	
}
