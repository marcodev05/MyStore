package com.tsk.ecommerce.service.product;

import java.util.List;
import java.util.Optional;

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;

public interface ProductService {
	
	public Product create(Product product);
	
	public Product update(Long id, Product product);
	
	public List<Product> findAllProduct();
	
	public Product getProductById(Long id);
	
	public void deleteProduct(Long id);
	
	public Product addToStock(Long id, Integer qty);
	
	public List<Picture> getAllPictureByProduct(Long idProduct);
	
	public List<Product> findProductByName(String name);
	
//	public Product addPicture(Long idProduct, Picture picture);
	
}
