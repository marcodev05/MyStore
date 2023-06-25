package com.tsk.ecommerce.controllers;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.ProductSearchRequest;
import com.tsk.ecommerce.dtos.responses.PageableResponse;
import com.tsk.ecommerce.services.product.CrudProductService;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.product.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import javax.validation.Valid;

import static com.tsk.ecommerce.common.ConstantsApp.*;

@CrossOrigin("*")
@RestController
@RequestMapping(PUBLIC_URL+"/products")
public class ProductController  {

	private final ProductService service;
	private final CrudProductService crudProductService;

	public ProductController(ProductService service, CrudProductService crudProductService) {
		this.service = service;
		this.crudProductService = crudProductService;
	}

	@Operation(summary = "Get all products")
	@GetMapping
	public ResponseEntity<PageableResponse<List<Product>>> searchProduct(@Valid ProductSearchRequest request){
		return new ResponseEntity<>(crudProductService.searchProduct(request), HttpStatus.OK);
	}

	@GetMapping("{id}")
	@Operation(summary = "Get a product by Id")
	public ResponseEntity<Product> getProductById(@PathVariable("id")Long id){
		return new ResponseEntity<>(crudProductService.getProductById(id), HttpStatus.OK);
	}

	@GetMapping("{id}/pictures")
	@Operation(summary = "Get All pictures by product")
	public ResponseEntity<List<Picture>> getPicturesByProduct(@PathVariable("id")Long id){
		return new ResponseEntity<>(service.getAllPictureByProduct(id), HttpStatus.OK);
	}

	@GetMapping("name/{name}")
	@Operation(summary = "Get list of products by name")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name")String nameProduct){
		return new ResponseEntity<>(service.findProductByName(nameProduct), HttpStatus.OK);
	}

}
