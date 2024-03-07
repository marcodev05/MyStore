package com.tsk.ecommerce.dtos.requests.products;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDto {

	@NotBlank(message = "Product name is required")
	private String name;

	@NotBlank(message = "code is required")
	private String code;
	
	@NotBlank(message = "Description is required")
	private String description;

	@NotNull(message = "category is required")
	private Long categoryId;

}
