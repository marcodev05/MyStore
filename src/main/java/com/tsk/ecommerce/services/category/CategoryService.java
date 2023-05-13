package com.tsk.ecommerce.services.category;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;

public interface CategoryService {
	
	public Category create(CategoryRequest category);
	
	public Category update(Integer id, Category category);
	
	public List<Category> findAllCategory();
	
	public Category getCategoryById(Integer id);
	
	public List<Product> getAllProductsByCategory(Integer idCategory);
	
	public void deleteCategory(Integer id);
	
}
