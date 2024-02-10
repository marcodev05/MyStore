package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsk.ecommerce.dtos.requests.products.ProductSearchRequest;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tsk.ecommerce.dtos.requests.products.ProductRequest;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;

import static com.tsk.ecommerce.common.ConstantsApp.*;

@RestController
public class ProductController  {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	/************************** *********** *********************\
	 * 							PUBLIC ROUTES
	 *************************************************************/
	@Operation(summary = "Get all products")
	@GetMapping(PUBLIC_URL + "/products")
	public Response<List<Product>> searchProduct(@Valid ProductSearchRequest request){
		return ResponseFactory.success(service.searchProduct(request));
	}

	@Operation(summary = "Get a product by Id")
	@GetMapping(PUBLIC_URL + "/products/{id}")
	public Response<Product> getProductById(@PathVariable("id")Long id){
		return ResponseFactory.success(service.getProductById(id));
	}

	@Operation(summary = "Get list of products by name")
	@GetMapping(PUBLIC_URL + "/products/name/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name")String nameProduct){
		return new ResponseEntity<>(service.findProductByName(nameProduct), HttpStatus.OK);
	}

	/************************** *********** *********************\
	 * 							ADMIN ROUTES
	 *************************************************************/

	@Operation(summary = "Add a new product")
	@ApiResponse(responseCode = "201", description = "Product is created")
	@PostMapping(ADMIN_URL + "/products/add")
	public Response<Product> addProduct(@Valid ProductRequest product, BindingResult bindingResult) {
		return ResponseFactory.created(service.create(product, bindingResult));
	}

	@Operation(summary = "Update a product by Id")
	@PutMapping(ADMIN_URL + "/products/{id}/update")
	public Response<Product> updateProduct(@Valid UpdateProductRequest productRequest, BindingResult bindingResult, @PathVariable("id") Long id) {
		return ResponseFactory.success(service.update(id, productRequest, bindingResult));
	}

	@Operation(summary = "Add to stock of product")
	@GetMapping(ADMIN_URL + "/products/{id}/addStock/{qty}")
	public Response<Product> addStockProduct( @PathVariable("id") Long id, @PathVariable("qty") Integer qty) {
		return ResponseFactory.success(service.addToStock(id, qty));
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
