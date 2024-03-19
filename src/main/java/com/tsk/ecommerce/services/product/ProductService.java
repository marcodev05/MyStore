package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.products.ProductRequestDto;
import com.tsk.ecommerce.dtos.requests.products.ProductSearchDto;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.entities.Product;
import org.springframework.validation.BindingResult;

public interface ProductService {
	Product create(ProductRequestDto product);
	Product update(Long id, UpdateProductRequest product);
	PaginationResponse<List<Product>> searchProduct(ProductSearchDto request);
	Product getProductById(Long id);
	void deleteProduct(Long id);
}
