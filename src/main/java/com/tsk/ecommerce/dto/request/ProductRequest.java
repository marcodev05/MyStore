package com.tsk.ecommerce.dto.request;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.tsk.ecommerce.entities.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

	@NotBlank(message = "le nom du produit est obligatoire")
	private String nameProduct;
	
	@NotBlank(message = "Ce champs est obligatoire")
	private String description;
	
	private Double price;

	private Integer stock;
	
	private Collection<Picture> pictures;
	
	private Integer idCategory;
	
}
