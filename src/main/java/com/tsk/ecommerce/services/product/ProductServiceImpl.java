package com.tsk.ecommerce.services.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.ProductRepository;
import com.tsk.ecommerce.services.category.CategoryService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryService categoryService;

	public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
	}

	@Override
	public Product create(ProductRequest product) {
		Product p = new Product();
				p.setDescription(product.getDescription());
				p.setNameProduct(product.getNameProduct());
				p.setPrice(product.getPrice());
				p.setStock(product.getStock());
				p.setAvailable(true);
				p.setCategory(categoryService.getCategoryById(product.getIdCategory()));
				//p.setCreatedAt(Date.from(Instant.now()));
		return productRepository.save(p);
	}
	
	
	@Override
	public Product update(Long id, ProductRequest productRequest) {
		Product p = this.getProductById(id);
		p.setNameProduct(productRequest.getNameProduct());
		p.setDescription(productRequest.getDescription());
		p.setPrice(productRequest.getPrice());
		p.setStock(productRequest.getStock());
		Category category = categoryService.getCategoryById(productRequest.getIdCategory());
		p.setCategory(category);
		return productRepository.save(p);
	}

	
	@Override
	public List<Product> findAllProduct() {
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
		if (p.getStock() > 0) {
			p.setAvailable(true);
		}
		return productRepository.save(p);
	}

	@Override
	public List<Picture> getAllPictureByProduct(Long idProduct) {
		return productRepository.findAllPicturesByProduct(idProduct);
	}

	@Override
	public List<Product> findProductByName(String name) {
		return productRepository.findByNameProductContains(name)
				.orElseThrow(() -> new ResourceNotFoundException("Le resultat de la recherche est vide"));
	}

	@Override
	public void reduceQtyByOrderLine(OrderLine orderLine) {
		Product p = this.getProductById(orderLine.getProduct().getIdProduct());
		Integer restStock = p.getStock() - orderLine.getQuantity();
		p.setStock(restStock);
		if (restStock == 0) {
			p.setAvailable(false);
		}
		productRepository.save(p);
	}

}
