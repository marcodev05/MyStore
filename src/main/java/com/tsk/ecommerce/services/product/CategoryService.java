package com.tsk.ecommerce.services.product;

import java.util.ArrayList;
import java.util.List;

import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.services.ObjectFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.repositories.CategoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements ICrudCategoryService {

	private final CategoryRepository categoryRepository;
	
	@Override
	public Category create(CategoryRequest category) {
		Category c = new Category();
		c.setName(category.getName());
		c.setDescription(category.getDescription());
		//Todo upload image
		return categoryRepository.save(c);
	}

	@Override
	public Category update(Integer id, Category category) {
		Category c = this.getCategoryById(id);
		c.setName(category.getName());
		c.setDescription(category.getDescription());
		//Todo update image if not null
		return categoryRepository.save(c);
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Integer id) {
		return ObjectFinder.findById(categoryRepository, "category", id);
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
	}

	public List<Product> getAllProductsByCategory(Integer idCategory) {
		//TODO find products by category
		return new ArrayList<>();
	}

}
