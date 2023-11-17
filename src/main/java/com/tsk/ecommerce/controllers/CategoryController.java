package com.tsk.ecommerce.controllers;

import java.util.List;

import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import com.tsk.ecommerce.services.product.CategoryService;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
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

	@Operation(summary = "Get all categories")
	@GetMapping(PUBLIC_URL + "/categories")
	public Response<List<Category>> getAllCategories(){
		return ResponseFactory.success(service.findAllCategory());
	}

	@Operation(summary = "Get a category by Id")
	@GetMapping(PUBLIC_URL + "/categories/{id}")
	public Response<Category> getCategoryById(@PathVariable("id")Integer id){
		return ResponseFactory.success(service.getCategoryById(id));
	}

	@Operation(summary = "Get all Products by category id")
	@GetMapping(PUBLIC_URL + "/categories/{id}/products")
	public Response<List<Product>> getAllProductsByCategory(@PathVariable("id") Integer categoryId) {
		return ResponseFactory.success(service.getAllProductsByCategory(categoryId));
	}

	@Operation(summary = "Add a new Category")
	@ApiResponse(responseCode = "201", description = "Category is created")
	@PostMapping(ADMIN_URL + "/categories")
	public Response<Category> addCategory(@Valid CategoryRequest category, BindingResult bindingResult) {
		return ResponseFactory.created(service.create(category, bindingResult));
	}

	@Operation(summary = "Update a category by id")
	@PostMapping(ADMIN_URL + "/categories/{id}/update")
	public Response<Category> updateCategory(@PathVariable("id") Integer id, @Valid CategoryRequest request, BindingResult bindingResult) {
		return ResponseFactory.success(service.update(id, request, bindingResult));
	}

	@Operation(summary = "Delete a category by Id")
	@DeleteMapping(ADMIN_URL + "/categories/{id}")
	public Response<String> deleteCategoryById(@PathVariable("id") Integer id) {
		service.deleteCategory(id);
		return ResponseFactory.success("Category deleted successfully");
	}

}
