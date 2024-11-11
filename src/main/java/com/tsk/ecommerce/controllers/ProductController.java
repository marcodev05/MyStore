package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.products.ProductSearchDto;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tsk.ecommerce.dtos.requests.products.ProductRequestDto;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;

import static com.tsk.ecommerce.common.ConstantsApp.*;

@RestController
@AllArgsConstructor
public class ProductController  {

	private final ProductService service;

	@Operation(summary = "Get all products")
	@GetMapping(PUBLIC_URL + "/products")
	public Response<PaginationResponse<List<Product>>> searchProduct(@Valid ProductSearchDto request){
		return ResponseFactory.success(service.searchProduct(request));
	}

	@Operation(summary = "Get a product by Id")
	@GetMapping(PUBLIC_URL + "/products/{id}")
	public Response<Product> getProductById(@PathVariable("id")Long id){
		return ResponseFactory.success(service.getProductById(id));
	}

	@Operation(summary = "Add a new product")
	@ApiResponse(responseCode = "201", description = "Product is created")
	@PostMapping(ADMIN_URL + "/products")
	public ResponseEntity<Response<Product>> addProduct(@Valid ProductRequestDto product) {
		return ResponseFactory.created(service.create(product));
	}

	@Operation(summary = "Update a product by Id")
	@PostMapping(ADMIN_URL + "/products/{id}/update")
	public Response<Product> updateProduct(@Valid UpdateProductRequest productRequest, @PathVariable("id") Long id) {
		return ResponseFactory.success(service.update(id, productRequest));
	}

	@Operation(summary = "Delete a product by Id")
	@DeleteMapping(ADMIN_URL + "/products/{id}")
	public Response<Map<String, Boolean>> deleteProduct(@PathVariable("id") Long id) {
		service.deleteProduct(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return ResponseFactory.success(response);
	}

}
