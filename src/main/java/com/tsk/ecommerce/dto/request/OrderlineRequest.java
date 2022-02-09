package com.tsk.ecommerce.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class OrderlineRequest {

		@NotBlank
		private Long idProduct;
		
		@Min(1)
		private Integer quantity;

		public OrderlineRequest(@NotBlank Long idProduct, @Min(1) Integer quantity) {
			super();
			this.idProduct = idProduct;
			this.quantity = quantity;
		}

		public OrderlineRequest() {
			super();
		}

		public Long getIdProduct() {
			return idProduct;
		}

		public void setIdProduct(Long idProduct) {
			this.idProduct = idProduct;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		
		
		
		
}
