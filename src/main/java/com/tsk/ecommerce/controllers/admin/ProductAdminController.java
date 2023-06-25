package com.tsk.ecommerce.controllers.admin;

import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.dtos.responses.ProductResponseDTO;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.product.CrudProductService;
import com.tsk.ecommerce.services.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.tsk.ecommerce.common.ConstantsApp.SELLER_URL;

@CrossOrigin("*")
@RestController
@RequestMapping(SELLER_URL+ "/products")
public class ProductAdminController {

	private final ProductService service;
	private final CrudProductService crudProductService;

	public ProductAdminController(ProductService service, CrudProductService crudProductService) {
		this.service = service;
		this.crudProductService = crudProductService;
	}

	@Operation(summary = "Add a new product")
	@ApiResponse(responseCode = "201", description = "Product is created")
	@PostMapping("add")
	public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequest product) {
		return new ResponseEntity<>(crudProductService.create(product), HttpStatus.CREATED);
	}

	@Operation(summary = "Update a product by Id")
	@PutMapping("{id}/update")
	public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable("id") Long id) {
		return new ResponseEntity<>(crudProductService.update(id, productRequest), HttpStatus.OK);
	}

	@Operation(summary = "Add to stock of product")
	@GetMapping("{id}/addStock/{qty}")
	public ResponseEntity<Product> addStockProduct( @PathVariable("id") Long id, @PathVariable("qty") Integer qty) {
		return new ResponseEntity<>(service.addToStock(id, qty), HttpStatus.OK);
	}

	@Operation(summary = "Delete a product by Id")
	@DeleteMapping("delete/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable("id") Long id) {
		crudProductService.deleteProduct(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
