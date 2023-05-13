package com.tsk.ecommerce.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderlineRequest {

		@NotBlank
		private Long idProduct;
		
		@Min(1)
		private Integer quantity;
		
}
