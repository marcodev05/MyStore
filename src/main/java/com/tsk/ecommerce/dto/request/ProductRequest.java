package com.tsk.ecommerce.dto.request;

import java.util.Collection;

import javax.validation.constraints.NotBlank;

import com.tsk.ecommerce.entities.Picture;

public class ProductRequest {

	@NotBlank(message = "le nom du produit est obligatoire")
	private String nameProduct;
	
	@NotBlank(message = "Ce champs est obligatoire")
	private String description;
	
	private Double price;

	private Integer stock;
	
	private Collection<Picture> pictures;
	
	private Integer idCategory;

	public ProductRequest(@NotBlank(message = "le nom du produit est obligatoire") String nameProduct,
			@NotBlank(message = "Ce champs est obligatoire") String description, Double price, Integer stock,
			Collection<Picture> pictures, Integer idCategory) {
		super();
		this.nameProduct = nameProduct;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.pictures = pictures;
		this.idCategory = idCategory;
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

	public Collection<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<Picture> pictures) {
		this.pictures = pictures;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	
	
	
}
