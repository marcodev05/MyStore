package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.ProductSearchRequest;
import com.tsk.ecommerce.dtos.responses.ProductResponseDTO;
import com.tsk.ecommerce.services.mappers.ProductMapperService;
import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.ProductRepository;
import com.tsk.ecommerce.services.category.CategoryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService, CrudProductService {

	private final ProductRepository productRepository;
	private final CategoryService categoryService;
	private final ProductMapperService productMapperService;

	public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductMapperService productMapperService) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
		this.productMapperService = productMapperService;
	}

	@Override
	public ProductResponseDTO create(ProductRequest product) {
		Product p = new Product();
		p.setDescription(product.getDescription());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		p.setStock(product.getStock());
		p.setCategory(categoryService.getCategoryById(product.getCategoryId()));
		return productMapperService.toResponseDto(productRepository.save(p));
	}

	@Override
	public ProductResponseDTO update(Long id, ProductRequest productRequest) {
		Product p = this.getProductById(id);
		p.setName(productRequest.getName());
		p.setDescription(productRequest.getDescription());
		p.setPrice(productRequest.getPrice());
		p.setStock(productRequest.getStock());
		Category category = categoryService.getCategoryById(productRequest.getCategoryId());
		p.setCategory(category);
		return productMapperService.toResponseDto(productRepository.save(p));
	}

	public List<Product> searchProduct(ProductSearchRequest request) {
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductById(Long id) {
		Product p = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product is not found"));
		return p;
	}

	@Override
	public void deleteProduct(Long id) {
		Product p = this.getProductById(id);
		productRepository.delete(p);
	}

	@Override
	public Product addToStock(Long id, Integer qty) {
		Product p = this.getProductById(id);
		p.setStock(p.getStock() + qty);
		return productRepository.save(p);
	}

	@Override
	public List<Picture> getAllPictureByProduct(Long idProduct) {
		return productRepository.findAllPicturesByProduct(idProduct);
	}

	@Override
	public List<Product> findProductByName(String name) {
		return productRepository.findByNameContains(name)
				.orElseThrow(() -> new ResourceNotFoundException("Le resultat de la recherche est vide"));
	}

	@Override
	public void reduceQtyByOrderLine(OrderLine orderLine) {
		Product p = this.getProductById(orderLine.getProduct().getId());
		Integer restStock = p.getStock() - orderLine.getQuantity();
		p.setStock(restStock);
		productRepository.save(p);
	}
}
