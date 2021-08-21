package com.tsk.ecommerce.resource;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.service.category.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin
@RestController
public class CategoryResource  {
	
	private static final String ADMIN = "/admin/v1/categories";
	private static final String PUBLIC = "/api/v1/categories";

	@Autowired
	CategoryService service;
	 
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 */
	
	@Operation(summary = "Add a new Category")
	@ApiResponse(responseCode = "201", description = "Category is created")
	@PostMapping(ADMIN + "/add")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		System.out.print("je suis executé");
		Category c = service.create(category);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	
	
	@Operation(summary = "Get all categories")
	@GetMapping(PUBLIC)
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = service.findAllCategory();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Get a category by Id")
	@GetMapping(PUBLIC + "/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id")Integer id){
		Category c = service.getCategoryById(id);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Update a category by id")
	@PutMapping(ADMIN + "/update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
		Category c = service.update(id, category);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Delete a category by Id")
	@DeleteMapping(ADMIN + "/delete/{id}")
	public Map<String, Boolean> deleteCategoryById(@PathVariable("id") Integer id) {
		service.deleteCategory(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}
	
	
	
	@Operation(summary = "Get all Products by category id")
	@GetMapping(PUBLIC + "/{id}/products")
	public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("id") Integer idCategory){
		List<Product> products = service.getAllProductsByCategory(idCategory);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
