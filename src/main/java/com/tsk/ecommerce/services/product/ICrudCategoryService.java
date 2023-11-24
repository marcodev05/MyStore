package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.entities.Category;
import org.springframework.validation.BindingResult;

public interface ICrudCategoryService {
	
	Category create(CategoryRequest category, BindingResult bindingResult);
	
	Category update(Long id, CategoryRequest request, BindingResult bindingResult);
	
	List<Category> findAllCategory();
	
	Category getCategoryById(Long id);

	void deleteCategory(Long id);
	
}
