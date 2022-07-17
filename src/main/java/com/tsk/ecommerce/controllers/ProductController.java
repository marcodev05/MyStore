package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.dto.request.ProductRequest;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.service.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin("*")
@RestController
public class ProductController  {
	
	private static final String ADMIN = "/admin/v1/products";
	private static final String PUBLIC = "/api/v1/products";
	
	@Autowired
	ProductService service;
	
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 */
	
	@Operation(summary = "Get all products")
	@GetMapping(PUBLIC)
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> products = service.findAllProduct();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get a product by Id")
	@GetMapping(PUBLIC + "/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id")Long id){
		Product product = service.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Get All pictures by product")
	@GetMapping(PUBLIC + "/{id}/pictures")
	public ResponseEntity<List<Picture>> getPicturesByProduct(@PathVariable("id")Long id){
		List<Picture> pictures = service.getAllPictureByProduct(id);
		return new ResponseEntity<>(pictures, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Get list of products by name")
	@GetMapping(PUBLIC + "/name/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name")String nameProduct){
		List<Product> products = service.findProductByName(nameProduct);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Add a new product")
	@ApiResponse(responseCode = "201", description = "Product is created")
	@RequestMapping(value= 	PUBLIC + "/add", method=RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody ProductRequest product) {
		Product p = service.create(product);
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}
	
	
	


	/************************** *********** *********************\
	 * 							ADMIN ROUTES
	 *************************************************************/

	@Operation(summary = "Update a product by Id")
	@PutMapping(ADMIN + "/update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Long id ) {
		Product p = service.update(id, product);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}


	@Operation(summary = "Add to stock of product")
	@PutMapping(ADMIN + "/addStock/{id}/{qty}")
	public ResponseEntity<Product> addStockProduct( @PathVariable("id") Long id, @PathVariable("qty") Integer qty) {
		Product p = service.addToStock(id, qty);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	


	@Operation(summary = "Delete a product by Id")
	@DeleteMapping(ADMIN + "/delete/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable("id") Long id) {
		service.deleteProduct(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}
	
	
	
}
