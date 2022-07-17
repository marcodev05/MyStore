package com.tsk.ecommerce.dto.request;

import java.util.Collection;

import javax.validation.constraints.NotBlank;

import com.tsk.ecommerce.entities.Picture;
import lombok.Data;

@Data
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
	
}
