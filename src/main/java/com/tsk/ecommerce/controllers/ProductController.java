package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tsk.ecommerce.dto.request.ProductRequest;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.service.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;

import static com.tsk.ecommerce.utils.Constants.ADMIN_URL;
import static com.tsk.ecommerce.utils.Constants.PUBLIC_URL;

@CrossOrigin("*")
@RestController
public class ProductController  {

	@Autowired
	ProductService service;
	
	/**
	 * Documentation path
	 * 
	 * http://localhost:8081/swagger-ui.html
	 */
	
	@Operation(summary = "Get all products")
	@GetMapping(PUBLIC_URL)
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> products = service.findAllProduct();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get a product by Id")
	@GetMapping(PUBLIC_URL + "/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id")Long id){
		Product product = service.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	

	@Operation(summary = "Get All pictures by product")
	@GetMapping(PUBLIC_URL + "/{id}/pictures")
	public ResponseEntity<List<Picture>> getPicturesByProduct(@PathVariable("id")Long id){
		List<Picture> pictures = service.getAllPictureByProduct(id);
		return new ResponseEntity<>(pictures, HttpStatus.OK);
	}
	

	@Operation(summary = "Get list of products by name")
	@GetMapping(PUBLIC_URL + "/name/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name")String nameProduct){
		List<Product> products = service.findProductByName(nameProduct);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	

	@Operation(summary = "Add a new product")
	@ApiResponse(responseCode = "201", description = "Product is created")
	@PostMapping(PUBLIC_URL + "/add")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest product) {
		Product p = service.create(product);
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}
	
	

	/************************** *********** *********************\
	 * 							ADMIN ROUTES
	 *************************************************************/

	@Operation(summary = "Update a product by Id")
	@PutMapping(ADMIN_URL + "/update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Long id ) {
		Product p = service.update(id, product);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}


	@Operation(summary = "Add to stock of product")
	@PutMapping(ADMIN_URL + "/addStock/{id}/{qty}")
	public ResponseEntity<Product> addStockProduct( @PathVariable("id") Long id, @PathVariable("qty") Integer qty) {
		Product p = service.addToStock(id, qty);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}


	@Operation(summary = "Delete a product by Id")
	@DeleteMapping(ADMIN_URL + "/delete/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable("id") Long id) {
		service.deleteProduct(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
