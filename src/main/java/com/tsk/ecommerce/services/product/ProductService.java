package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;

public interface ProductService {
	
	public Product create(ProductRequest product);
	
	public Product update(Long id, ProductRequest product);
	
	public List<Product> findAllProduct();
	
	public Product getProductById(Long id);
	
	public void deleteProduct(Long id);
	
	public Product addToStock(Long id, Integer qty);
	
	public List<Picture> getAllPictureByProduct(Long idProduct);
	
	public List<Product> findProductByName(String name);
	
	public void reduceQtyByOrderLine(OrderLine orderLine);
	
//	public Product addPicture(Long idProduct, Picture picture);
	
}
