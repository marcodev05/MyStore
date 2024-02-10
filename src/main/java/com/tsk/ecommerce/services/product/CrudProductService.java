package com.tsk.ecommerce.services.product;

import com.tsk.ecommerce.dtos.requests.products.ProductRequest;
import com.tsk.ecommerce.dtos.requests.products.ProductSearchRequest;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.entities.Product;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CrudProductService {
	Product create(ProductRequest product, BindingResult bindingResult);
	Product update(Long id, UpdateProductRequest product,  BindingResult bindingResult);
	List<Product> searchProduct(ProductSearchRequest request);
	Product getProductById(Long id);
	void deleteProduct(Long id);
}
