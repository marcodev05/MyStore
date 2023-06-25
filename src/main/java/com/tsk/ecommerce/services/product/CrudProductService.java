package com.tsk.ecommerce.services.product;

import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.dtos.requests.ProductSearchRequest;
import com.tsk.ecommerce.dtos.responses.ProductResponseDTO;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;

import java.util.List;

public interface CrudProductService {
	ProductResponseDTO create(ProductRequest product);
	ProductResponseDTO update(Long id, ProductRequest product);
	List<Product> searchProduct(ProductSearchRequest request);
	Product getProductById(Long id);
	void deleteProduct(Long id);
}
