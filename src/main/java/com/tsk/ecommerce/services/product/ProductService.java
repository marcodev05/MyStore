package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.products.ProductRequest;
import com.tsk.ecommerce.dtos.requests.products.ProductSearchDto;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.entities.Product;
import org.springframework.validation.BindingResult;

public interface ProductService {
	Product addToStock(Long id, Integer qty);
	List<Product> findProductByName(String name);
	Product create(ProductRequest product, BindingResult bindingResult);
	Product update(Long id, UpdateProductRequest product, BindingResult bindingResult);
	List<Product> searchProduct(ProductSearchDto request);
	Product getProductById(Long id);
	void deleteProduct(Long id);
}
