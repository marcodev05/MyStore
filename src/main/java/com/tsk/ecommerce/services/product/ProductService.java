package com.tsk.ecommerce.services.product;

import java.util.List;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;

public interface ProductService {
	Product addToStock(Long id, Integer qty);
	List<Picture> getAllPictureByProduct(Long idProduct);
	List<Product> findProductByName(String name);
	void reduceQtyByOrderLine(OrderLine orderLine);
//	Product addPicture(Long idProduct, Picture picture);
	
}
