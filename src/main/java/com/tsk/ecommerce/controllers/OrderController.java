package com.tsk.ecommerce.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.dtos.requests.OrderRequest;
import com.tsk.ecommerce.entities.Orders;
import com.tsk.ecommerce.services.orders.OrderService;
import com.tsk.ecommerce.services.messageries.email.NotificationEmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import static com.tsk.ecommerce.tools.ConstantsApp.*;

@CrossOrigin("*")
@RestController
@Transactional
public class OrderController {

	@Autowired
	OrderService service;
	
	@Autowired
	NotificationEmailService notificationEmailService;
	 
	/**
	 * Documentation path
	 *
	 * http://localhost:8081/swagger-ui.html
	 */


	/************************** *********** *********************\
	 * 							USER ROUTES
	 *************************************************************/

	@Operation(summary = "Create a new Orders")
	@ApiResponse(responseCode = "201", description = "Order is created")
	@PostMapping(USER_URL + "/orders/add")
	public ResponseEntity<Orders> createOrder(@Valid @RequestBody OrderRequest orderRequest) throws IOException {
		Orders ord = service.create(orderRequest);
		//notificationService.sendNotification(ord);
		return new ResponseEntity<>(ord, HttpStatus.CREATED);
	}


	@Operation(summary = "Delete my own Order by ID")
	@DeleteMapping(USER_URL + "/orders/delete/{id}")
	public Map<String, Boolean> deleteOrdersById(@PathVariable("id")Long id) {
		service.deleteOrders(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}


	/************************** *********** *********************\
	 * 							SELLER ROUTES
	 *************************************************************/
	

	@Operation(summary = "Get all new Order for seller notification")
	@GetMapping(SELLER_URL + "/orders/newOrders")
	public ResponseEntity<List<Orders>> getAllNewOrders(){
		List<Orders> orders = service.getAllNewCommands();
		return new ResponseEntity<>(orders , HttpStatus.OK);
	}
	

	@Operation(summary = "Get a Order by Id")
	@GetMapping(SELLER_URL + "/orders/{id}")
	public ResponseEntity<Orders> getOrdersById(@PathVariable("id")Long id){
		Orders ord = service.getOrdersById(id);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}

//	@Operation(summary = "Update an Order by id")
//	@PutMapping(USER_URL + "/orders/update/{id}")
//	public ResponseEntity<Orders> updateOrders(@PathVariable("id") Long id, @RequestBody Orders Orders) {
//		Orders c = service.update(id, Orders);
//		return new ResponseEntity<>(c, HttpStatus.OK);
//	}

}
