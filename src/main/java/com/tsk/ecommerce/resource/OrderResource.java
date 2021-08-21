package com.tsk.ecommerce.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.tsk.ecommerce.service.orders.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin
@RestController
public class OrderResource {
	
	private static final String ADMIN = "/admin/v1/orders";
	private static final String PUBLIC = "/api/v1/orders";
	
	@Autowired
	OrderService service;
	 
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 */
	
	@Operation(summary = "Add a new Orders")
	@ApiResponse(responseCode = "201", description = "Order is created")
	@PostMapping(PUBLIC + "/add")
	public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
		Orders ord = service.create(order);
		return new ResponseEntity<>(ord, HttpStatus.CREATED);
	}
	
	
//	@Operation(summary = "Get all categories")
//	@GetMapping()
//	public ResponseEntity<List<Orders>> getAllCategories(){
//		List<Orders> categories = service.findAllOrders();
//		return new ResponseEntity<>(categories, HttpStatus.OK);
//	}
	
	
	@Operation(summary = "Get a Order by Id")
	@GetMapping(PUBLIC + "/{id}")
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
