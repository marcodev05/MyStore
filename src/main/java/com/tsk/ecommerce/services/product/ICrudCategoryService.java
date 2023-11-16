package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.entities.Category;

public interface CrudCategoryService {
	
	Category create(CategoryRequest category);
	
	Category update(Integer id, Category category);
	
	List<Category> findAllCategory();
	
	Category getCategoryById(Integer id);
	
	void deleteCategory(Integer id);
	
}
