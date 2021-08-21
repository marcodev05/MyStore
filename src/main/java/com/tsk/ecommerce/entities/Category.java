package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Category implements Serializable{
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer idCateg;
	private String name;
	private String description;
	
	@JsonBackReference
	@OneToMany(mappedBy = "category")
	private Collection<Product> products;
	
	public Category() {
		super();
	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Integer getIdCateg() {
		return idCateg;
	}

	public void setIdCateg(Integer idCateg) {
		this.idCateg = idCateg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	


	
}
