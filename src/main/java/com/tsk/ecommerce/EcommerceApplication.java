package com.tsk.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tsk.ecommerce.entities.Address;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.model.OrderRequest;
import com.tsk.ecommerce.model.OrderlineRequest;
import com.tsk.ecommerce.repository.PictureRepository;
import com.tsk.ecommerce.repository.RoleEntityRepository;
import com.tsk.ecommerce.service.address.AddressService;
import com.tsk.ecommerce.service.category.CategoryService;
import com.tsk.ecommerce.service.customer.CustomerService;
import com.tsk.ecommerce.service.orders.OrderService;
import com.tsk.ecommerce.service.product.ProductService;
import com.tsk.ecommerce.service.user.UserService;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	@Autowired
	RoleEntityRepository roleEntityRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PictureRepository pictureRepo;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * compte user
		 */
		RoleEntity r1 = new RoleEntity ("ROLE_ADMIN");
		RoleEntity r2 = new RoleEntity ("ROLE_USER");
		roleEntityRepository.save(r1);
		roleEntityRepository.save(r2);
		
		userService.register(new UserEntity("root", "root", "root@gmail.com"));
		
		
		
		Category c1 = categoryService.create(new Category("Materiel info", "outils et matériel informatique"));
		Product prod1 = productService.create(new Product("souris","souris sans fils avec garanti 1mois", 5000.0, 12, c1));
		
		Picture pic = new Picture("https://images.unsplash.com/photo-1527814050087-3793815479db?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1528&q=80",prod1);
		pictureRepo.save(pic);
		
		Address addr1 = new Address("Lot 127 A1/ 3283 Ambatomena", null, "manakara");
		Customer customer1 = new Customer("RAINIBE", "Jean", "r_jean56@gmail.com", "032 08 802 58", null);
		//customerService.create(customer1);
		
		OrderlineRequest line = new OrderlineRequest(prod1, 7);
		List<OrderlineRequest> lines = new ArrayList<OrderlineRequest>();
		lines.add(line);
		
		OrderRequest ordRequest = new OrderRequest("commande divers", customer1.getFirstName(), customer1.getLastName(), customer1.getEmail(), customer1.getEmail(), addr1.getLot(), addr1.getAddrPlus(), addr1.getCity(), lines);
		orderService.create(ordRequest);
	}

}
