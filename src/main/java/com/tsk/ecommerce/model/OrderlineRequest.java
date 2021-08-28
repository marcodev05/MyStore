package com.tsk.ecommerce.model;

import javax.validation.constraints.Min;

import com.tsk.ecommerce.entities.Product;

public class OrderlineRequest {

		private Product product;
		
		@Min(1)
		private Integer quantity;

		
		public OrderlineRequest(Product product, @Min(1) Integer quantity) {
			super();
			this.product = product;
			this.quantity = quantity;
		}

		public OrderlineRequest() {
			super();
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

	
		
}
