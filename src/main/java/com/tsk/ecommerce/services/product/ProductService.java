package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.entities.Product;

public interface ProductService extends CrudProductService  {
	Product addToStock(Long id, Integer qty);
	List<Product> findProductByName(String name);
}
