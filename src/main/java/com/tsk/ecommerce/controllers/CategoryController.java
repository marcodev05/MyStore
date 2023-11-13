package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.tsk.ecommerce.dtos.requests.CategoryRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;

import static com.tsk.ecommerce.common.ConstantsApp.PUBLIC_URL;
import static com.tsk.ecommerce.common.ConstantsApp.ADMIN_URL;

@RestController
public class CategoryController  {

	private final CategoryService service;

	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@Operation(summary = "Add a new Category")
	@ApiResponse(responseCode = "201", description = "Category is created")
	@PostMapping(ADMIN_URL + "/categories/add")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryRequest category) {
		return new ResponseEntity<>(service.create(category), HttpStatus.CREATED);
	}

	@Operation(summary = "Get all categories")
	@GetMapping(PUBLIC_URL + "/categories")
	public ResponseEntity<List<Category>> getAllCategories(){
		return new ResponseEntity<>(service.findAllCategory(), HttpStatus.OK);
	}

	@Operation(summary = "Get a category by Id")
	@GetMapping(PUBLIC_URL + "/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id")Integer id){
		return new ResponseEntity<>(service.getCategoryById(id), HttpStatus.OK);
	}

	@Operation(summary = "Update a category by id")
	@PutMapping(ADMIN_URL + "/categories/{id}/update")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
		return new ResponseEntity<>(service.update(id, category), HttpStatus.OK);
	}

	@Operation(summary = "Delete a category by Id")
	@DeleteMapping(ADMIN_URL + "/categories/{id}")
	public Map<String, Boolean> deleteCategoryById(@PathVariable("id") Integer id) {
		service.deleteCategory(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

	@Operation(summary = "Get all Products by category id")
	@GetMapping(PUBLIC_URL + "/categories/{id}/products")
	public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("id") Integer idCategory){
		return new ResponseEntity<>(service.getAllProductsByCategory(idCategory), HttpStatus.OK);
	}
}
