package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.category.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;

import static com.tsk.ecommerce.tools.ConstantsApp.PUBLIC_URL;
import static com.tsk.ecommerce.tools.ConstantsApp.SELLER_URL;

@CrossOrigin("*")
@RestController
public class CategoryController  {

	@Autowired
	CategoryService service;
	 
	/**
	 * Documentation path api
	 * 
	 * http://localhost:8081/swagger-ui.html
	 */
	
	@Operation(summary = "Add a new Category")
	@ApiResponse(responseCode = "201", description = "Category is created")
	@PostMapping(SELLER_URL + "/categories/add")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryRequest category) {
		Category c = service.create(category);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	

	@Operation(summary = "Get all categories")
	@GetMapping(PUBLIC_URL + "/categories")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = service.findAllCategory();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	

	@Operation(summary = "Get a category by Id")
	@GetMapping(PUBLIC_URL + "/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id")Integer id){
		Category c = service.getCategoryById(id);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	

	@Operation(summary = "Update a category by id")
	@PutMapping(SELLER_URL + "/categories/{id}/update")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
		Category c = service.update(id, category);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	

	@Operation(summary = "Delete a category by Id")
	@DeleteMapping(SELLER_URL + "/categories/delete/{id}")
	public Map<String, Boolean> deleteCategoryById(@PathVariable("id") Integer id) {
		service.deleteCategory(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}
	

	@Operation(summary = "Get all Products by category id")
	@GetMapping(PUBLIC_URL + "/categories/{id}/products")
	public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("id") Integer idCategory){
		List<Product> products = service.getAllProductsByCategory(idCategory);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
