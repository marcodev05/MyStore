package com.tsk.ecommerce.controllers.admin;

import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.services.category.CategoryService;
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
@RequestMapping(SELLER_URL + "/categories")
public class CategoryAdminController {

	private final CategoryService service;

	public CategoryAdminController(CategoryService service) {
		this.service = service;
	}

	@Operation(summary = "Add a new Category")
	@ApiResponse(responseCode = "201", description = "Category is created")
	@PostMapping("add")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryRequest category) {
		return new ResponseEntity<>(service.create(category), HttpStatus.CREATED);
	}

	@Operation(summary = "Update a category by id")
	@PutMapping("{id}/update")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
		return new ResponseEntity<>(service.update(id, category), HttpStatus.OK);
	}

	@Operation(summary = "Delete a category by Id")
	@DeleteMapping("{id}/delete")
	public Map<String, Boolean> deleteCategoryById(@PathVariable("id") Integer id) {
		service.deleteCategory(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
