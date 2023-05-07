package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Category implements Serializable{
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String name;
	private String description;

	@JsonBackReference
	@OneToMany(mappedBy = "category")
	private Collection<Product> products;
}
