package com.tsk.ecommerce.services.product;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	public Product create(ProductRequest product) {
		Product p = Product.builder()
				.nameProduct(product.getNameProduct())
				.description(product.getDescription())
				.price(product.getPrice())
				.stock(product.getStock())
				.available(true)
				.category(categoryService.getCategoryById(product.getIdCategory()))
				.createdAt(Date.from(Instant.now()))
				.build();
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
