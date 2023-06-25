package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.ProductSearchRequest;
import com.tsk.ecommerce.dtos.responses.PageableResponse;
import com.tsk.ecommerce.dtos.responses.ProductResponseDTO;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.services.ObjectFinder;
import com.tsk.ecommerce.services.mappers.ProductMapperService;
import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService, CrudProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductMapperService productMapperService;

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapperService productMapperService) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapperService = productMapperService;
	}

	@Override
	public ProductResponseDTO create(ProductRequest product) {
		Product p = new Product();
		p.setDescription(product.getDescription());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		p.setStock(product.getStock());

		Category category = ObjectFinder.findById(categoryRepository, "category", product.getCategoryId());
		p.setCategory(category);
		return productMapperService.toResponseDto(productRepository.save(p));
	}

	@Override
	public ProductResponseDTO update(Long id, ProductRequest productRequest) {
		Product p = this.getProductById(id);
		p.setName(productRequest.getName());
		p.setDescription(productRequest.getDescription());
		p.setPrice(productRequest.getPrice());
		p.setStock(productRequest.getStock());

		Category category = ObjectFinder.findById(categoryRepository, "category", productRequest.getCategoryId());
		p.setCategory(category);
		return productMapperService.toResponseDto(productRepository.save(p));
	}

	public PageableResponse<List<Product>> searchProduct(ProductSearchRequest request) {
		Pageable pageable = PageRequest.of(request.getPagination().getPage() - 1, request.getPagination().getSize());
		Page<Product> productPage = productRepository.findAll(pageable);
		return new PageableResponse<>(
				productPage.getContent(),
				productPage.getTotalPages(),
				productPage.getTotalElements());
	}
	
	@Override
	public Product getProductById(Long id) {
		return ObjectFinder.findById(productRepository, "product", id);
	}

	@Override
	public void deleteProduct(Long id) {
		Product p = ObjectFinder.findById(productRepository, "product", id);
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
