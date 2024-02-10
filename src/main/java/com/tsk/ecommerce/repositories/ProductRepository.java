package com.tsk.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select p.pictures from Product p where p.idProduct = ?1 ")
	List<Picture> findAllPicturesByProduct(Long idProduct);
	
	List<Product> findByNameProductContains(String nameProduct);
	
}
