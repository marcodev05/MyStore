package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Address implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAddress;
	@NotBlank(message = "Ce champs ne doit pas être vide")
	private String lot;
	private String addrPlus;
	@NotBlank(message = "Ce champs est obligatoire")
	@NotNull
	private String city;
	
	
	@OneToMany(mappedBy = "address")
	@JsonIgnore
	private Collection<Customer> customers;
	
	public Address() {
		super();
	}

	public Address(String lot, String addrPlus, String city) {
		super();
		this.lot = lot;
		this.addrPlus = addrPlus;
		this.city = city;
	}

	public Long getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
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

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}



}
