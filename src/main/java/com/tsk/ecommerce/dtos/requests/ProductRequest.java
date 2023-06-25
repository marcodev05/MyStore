package com.tsk.ecommerce.dtos.requests;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tsk.ecommerce.entities.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

	@NotBlank(message = "Product name is required")
	private String name;
	
	@NotBlank(message = "Description is required")
	private String description;

	@NotNull(message = "Price is required")
	@Min(value = 0, message = "la valeur minimal est 0")
	@Valid
	private Double price;

	@Min(value = 0, message = "la valeur minimal est 0")
	private Integer stock;
	
	private Collection<Picture> pictures;

	@NotNull(message = "category is required")
	private Integer categoryId;
}
