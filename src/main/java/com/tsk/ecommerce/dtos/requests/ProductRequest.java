package com.tsk.ecommerce.dtos.requests;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tsk.ecommerce.entities.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

	@NotBlank(message = "Product name is required")
	private String nameProduct;
	
	@NotBlank(message = "Description is required")
	private String description;

	@NotNull
	private Double price;

	private Integer stock;
	
	private Collection<Picture> pictures;

	@NotNull(message = "category is required")
	private Integer categoryId;
}
