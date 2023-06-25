package com.tsk.ecommerce.services.category;

import java.util.List;

import com.tsk.ecommerce.common.Pagination;
import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.dtos.responses.PageableResponse;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.services.ObjectFinder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

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
		return ObjectFinder.findById(categoryRepository, "category", id);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category c = this.getCategoryById(id);
		categoryRepository.delete(c);
	}

	@Override
	public PageableResponse<List<Product>> getAllProductsByCategory(Integer idCategory, Pagination pagination) {
		ObjectFinder.findById(categoryRepository, "category", idCategory);
		Pageable pageable = PageRequest.of(pagination.getPage() - 1, pagination.getSize());
		Page<Product> productPage = categoryRepository.findAllProductsByCategory(idCategory, pageable);
		return new PageableResponse<>(
				productPage.getContent(),
				productPage.getTotalPages(),
				productPage.getTotalElements());
	}

}
