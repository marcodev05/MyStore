package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Product implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	@NotBlank(message = "le nom du produie est obligatoire")
	private String nameProduct;
	@NotBlank(message = "Ce champs est obligatoire")
	private String description;
	@NotBlank(message = "Le prix est obligatoire")
	private Double price;
	
	@NotNull
	private Integer stock;
	private Boolean available;
	private Boolean selected;
	
	@Max(value = 5)
	@Min(value = 0)
	private Integer rate;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private Collection<Picture> pictures;
	
	@ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private Category category;
	
	@JsonBackReference
	@OneToMany(mappedBy = "product")
	private Collection<OrderLine> orderLines;
	
	public Product() {
		super();
	}


	public Product(String nameProduct, String description, Double price, Integer stock, Category category) {
		super();
		this.nameProduct = nameProduct;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}


	public Long getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}


	public String getNameProduct() {
		return nameProduct;
	}


	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public Boolean getAvailable() {
		return available;
	}


	public void setAvailable(Boolean available) {
		this.available = available;
	}


	public Boolean getSelected() {
		return selected;
	}


	public void setSelected(Boolean selected) {
		this.selected = selected;
	}




	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Collection<OrderLine> getOrderLines() {
		return orderLines;
	}


	public void setOrderLines(Collection<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}


	public Collection<Picture> getPictures() {
		return pictures;
	}


	public void setPictures(Collection<Picture> pictures) {
		this.pictures = pictures;
	}


	public Integer getRate() {
		return rate;
	}


	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	
	
	

}
