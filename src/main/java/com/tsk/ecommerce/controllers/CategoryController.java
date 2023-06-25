package com.tsk.ecommerce.controllers;

import java.util.List;

import com.tsk.ecommerce.common.Pagination;
import com.tsk.ecommerce.dtos.responses.PageableResponse;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.category.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import static com.tsk.ecommerce.common.ConstantsApp.PUBLIC_URL;

@CrossOrigin("*")
@RestController
@RequestMapping(PUBLIC_URL + "/categories")
public class CategoryController  {

	private final CategoryService service;

	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Get all categories")
	public ResponseEntity<List<Category>> getAllCategories(){
		return new ResponseEntity<>(service.findAllCategory(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	@Operation(summary = "Get a category by Id")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id")Integer id){
		return new ResponseEntity<>(service.getCategoryById(id), HttpStatus.OK);
	}

	@GetMapping("{id}/products")
	@Operation(summary = "Get all Products by category id")
	public ResponseEntity<PageableResponse<List<Product>>> getAllProductsByCategory(@PathVariable("id") Integer idCategory, Pagination pagination){
		return new ResponseEntity<>(service.getAllProductsByCategory(idCategory, pagination), HttpStatus.OK);
	}
}
