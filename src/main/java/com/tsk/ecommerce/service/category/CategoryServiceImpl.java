package com.tsk.ecommerce.service.category;

import java.util.List;

import com.tsk.ecommerce.dto.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category create(CategoryRequest category) {
		Category c = new Category();
		c.setName(category.getName());
		c.setDescription(category.getDescription());
		return categoryRepository.save(c);
	}

	
	@Override
	public Category update(Integer id, Category category) {
		Category c = this.getCategoryById(id);
		c.setName(category.getName());
		c.setDescription(category.getDescription());
		return categoryRepository.save(c);
	}

	
	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}
	

	@Override
	public Category getCategoryById(Integer id) {
		Category c = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catégorie introuvable ! "));
		return c;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category c = this.getCategoryById(id);
		categoryRepository.delete(c);
	}


	@Override
	public List<Product> getAllProductsByCategory(Integer idCategory) {
		return (List<Product>) categoryRepository.findAllProductsByCategory(idCategory);
	}

}
