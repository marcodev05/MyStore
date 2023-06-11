package com.tsk.ecommerce.services.category;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;

public interface CategoryService {
	
	Category create(CategoryRequest category);
	
	Category update(Integer id, Category category);
	
	List<Category> findAllCategory();
	
	Category getCategoryById(Integer id);
	
	List<Product> getAllProductsByCategory(Integer idCategory);
	
	void deleteCategory(Integer id);
	
}
