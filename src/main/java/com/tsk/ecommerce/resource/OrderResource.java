package com.tsk.ecommerce.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.Orders;
import com.tsk.ecommerce.model.NotificationMail;
import com.tsk.ecommerce.model.OrderRequest;
import com.tsk.ecommerce.service.orders.OrderService;
import com.tsk.ecommerce.utils.email.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin("*")
@RestController
@Transactional
public class OrderResource {
	
	private static final String ADMIN = "/admin/v1/orders";
	private static final String PUBLIC = "/api/v1/orders";
	
	@Autowired
	OrderService service;
	
	@Autowired
	NotificationService notificationService;
	 
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 * @throws IOException 
	 */ 
	
	@Operation(summary = "Add a new Orders")
	@ApiResponse(responseCode = "201", description = "Order is created")
	@PostMapping(PUBLIC + "/add")
	public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest) throws IOException {
		Orders ord = service.create(orderRequest);
		notificationService.sendNotification(ord);

		return new ResponseEntity<>(ord, HttpStatus.CREATED);
	}
	
	
	
	
	
	@Operation(summary = "Get all new Order for admin notification")
	@GetMapping(ADMIN + "/newOrders")
	public ResponseEntity<List<Orders>> getAllNewOrders(){
		List<Orders> orders = service.getAllNewCommands();
		return new ResponseEntity<>(orders , HttpStatus.OK);
	}
	
	
	
	
	
	@Operation(summary = "Get a Order by Id")
	@GetMapping(ADMIN + "/{id}")
	public ResponseEntity<Orders> getOrdersById(@PathVariable("id")Long id){
		Orders ord = service.getOrdersById(id);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Update an Order by id")
	@PutMapping(PUBLIC + "/update/{id}")
	public ResponseEntity<Orders> updateOrders(@PathVariable("id") Long id, @RequestBody Orders Orders) {
		Orders c = service.update(id, Orders);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Delete an Order by Id")
	@DeleteMapping(PUBLIC + "/delete/{id}")
	public Map<String, Boolean> deleteOrdersById(@PathVariable("id")Long id) {
		service.deleteOrders(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
