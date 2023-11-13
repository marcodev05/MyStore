package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsk.ecommerce.dtos.requests.ProductSearchRequest;
import com.tsk.ecommerce.services.product.CrudProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;

import static com.tsk.ecommerce.common.ConstantsApp.*;

@RestController
public class ProductController  {

	private final ProductService service;
	private final CrudProductService crudProductService;

	public ProductController(ProductService service, CrudProductService crudProductService) {
		this.service = service;
		this.crudProductService = crudProductService;
	}

	/************************** *********** *********************\
	 * 							PUBLIC ROUTES
	 *************************************************************/
	@Operation(summary = "Get all products")
	@GetMapping(PUBLIC_URL + "/products")
	public ResponseEntity<List<Product>> searchProduct(@Valid ProductSearchRequest request){
		System.out.println("list products");
		return new ResponseEntity<>(crudProductService.searchProduct(request), HttpStatus.OK);
	}

	@Operation(summary = "Get a product by Id")
	@GetMapping(PUBLIC_URL + "/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id")Long id){
		return new ResponseEntity<>(crudProductService.getProductById(id), HttpStatus.OK);
	}

	@Operation(summary = "Get All pictures by product")
	@GetMapping(PUBLIC_URL + "/products/{id}/pictures")
	public ResponseEntity<List<Picture>> getPicturesByProduct(@PathVariable("id")Long id){
		return new ResponseEntity<>(service.getAllPictureByProduct(id), HttpStatus.OK);
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
	public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest product) {
		return new ResponseEntity<>(crudProductService.create(product), HttpStatus.CREATED);
	}

	@Operation(summary = "Update a product by Id")
	@PutMapping(ADMIN_URL + "/products/{id}/update")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable("id") Long id) {
		return new ResponseEntity<>(crudProductService.update(id, productRequest), HttpStatus.OK);
	}

	@Operation(summary = "Add to stock of product")
	@GetMapping(ADMIN_URL + "/products/{id}/addStock/{qty}")
	public ResponseEntity<Product> addStockProduct( @PathVariable("id") Long id, @PathVariable("qty") Integer qty) {
		return new ResponseEntity<>(service.addToStock(id, qty), HttpStatus.OK);
	}

	@Operation(summary = "Delete a product by Id")
	@DeleteMapping(ADMIN_URL + "/products/delete/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable("id") Long id) {
		crudProductService.deleteProduct(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
