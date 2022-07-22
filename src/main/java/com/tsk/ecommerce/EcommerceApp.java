package com.tsk.ecommerce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tsk.ecommerce.dto.request.ProductRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.entities.auth.ERole;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.repository.PictureRepository;
import com.tsk.ecommerce.repository.RoleEntityRepository;
import com.tsk.ecommerce.service.category.CategoryService;
import com.tsk.ecommerce.service.customer.CustomerService;
import com.tsk.ecommerce.service.orders.OrderService;
import com.tsk.ecommerce.service.product.ProductService;
import com.tsk.ecommerce.service.user.UserService;



@SpringBootApplication
public class EcommerceApp implements CommandLineRunner {
	
	//@Autowired
	//RoleEntityRepository roleEntityRepository;
	
	//@Autowired
	//OrderService orderService;
	
	//@Autowired
	//PictureRepository pictureRepo;
	
	//@Autowired
	//ProductService productService;
	
	//@Autowired
	//CategoryService categoryService;
	
	
	//@Autowired
	//CustomerService customerService;
	
	//@Autowired
	//UserService userService;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(EcommerceApp.class, args);

	}

	
	
	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * compte user
		 */
		RoleEntity r1 = new RoleEntity (ERole.ROLE_ADMIN);
		RoleEntity r2 = new RoleEntity (ERole.ROLE_SELLER);
		RoleEntity r3 = new RoleEntity (ERole.ROLE_USER);
		//roleEntityRepository.save(r1);
		//roleEntityRepository.save(r2);
		//roleEntityRepository.save(r3);
		
		//userService.register(new UserEntity("root", "root1234", "root@gmail.com"));
		
		
		
		//Category c1 = categoryService.create(new Category("Materiel info", "outils et matériel informatique"));
		//Product prod1 = productService.create(new ProductRequest("souris","souris sans fils avec garanti 1mois", 5000.0, 12, null, c1.getIdCateg()));
		//Product prod2 = productService.create(new ProductRequest("Telephone nexus s6"," ram: 4go, memoire:32go ", 300000.0, 5, null, c1.getIdCateg()));
		
		//Picture pic = new Picture("https://images.unsplash.com/photo-1527814050087-3793815479db?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1528&q=80",prod1);
		//pictureRepo.save(pic);

	}
}
