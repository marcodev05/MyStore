package com.tsk.ecommerce.payload;

import javax.validation.constraints.Min;

import com.tsk.ecommerce.entities.Product;

public class OrderlineRequest {

		private Product produit;
		
		@Min(1)
		private Integer qte;

		
		public OrderlineRequest(Product produit, @Min(1) Integer qte) {
			super();
			this.produit = produit;
			this.qte = qte;
		}

		public OrderlineRequest() {
			super();
		}

		public Product getProduit() {
			return produit;
		}

		public void setProduit(Product product) {
			this.produit = product;
		}

		public Integer getQte() {
			return qte;
		}

		public void setQte(Integer qte) {
			this.qte = qte;
		}

	
		
}
