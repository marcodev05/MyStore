package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.products.ProductSearchRequest;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.services.ObjectFinder;
import com.tsk.ecommerce.services.mappers.ProductMapper;
import com.tsk.ecommerce.services.validators.FieldValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.dtos.requests.products.ProductRequest;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.ProductRepository;
import org.springframework.validation.BindingResult;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductMapper productMapper;

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
	}

	@Override
	public Product create(ProductRequest request, BindingResult bindingResult) {
		//String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FieldValidator.validate(bindingResult);
		Product p = productMapper.toProductEntity(request);
		//todo handle images
		return productRepository.save(p);
	}

	@Override
	public Product update(Long id, UpdateProductRequest productRequest, BindingResult bindingResult) {
		FieldValidator.validate(bindingResult);
		Product p = this.getProductById(id);
		productRequest.commitTo(p);
		//todo handle images
		p.setCategory(ObjectFinder.findById(categoryRepository, "category", productRequest.getCategoryId()));
		return productRepository.save(p);
	}

	public List<Product> searchProduct(ProductSearchRequest request) {
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductById(Long id) {
		return ObjectFinder.findById(productRepository, "product", id);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product addToStock(Long id, Integer qty) {
		Product p = this.getProductById(id);
		return productRepository.save(p);
	}

	@Override
	public List<Product> findProductByName(String name) {
		return productRepository.findByNameContains(name);
	}
}
