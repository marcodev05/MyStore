package com.tsk.ecommerce.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.services.orderLine.OrderLineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin
@RestController
public class OrderLineController {
	
	private static final String PUBLIC = "/api/v1/orderlines";
	private final OrderLineService service;

	public OrderLineController(OrderLineService service) {
		this.service = service;
	}

	@Operation(summary = "Add a new OrderLine")
	@ApiResponse(responseCode = "201", description = "OrderLine is created")
	@PostMapping(PUBLIC + "/add")
	public ResponseEntity<OrderLine> addOrderLine(@RequestBody OrderLine orderLine) {
		return new ResponseEntity<>(service.create(orderLine), HttpStatus.CREATED);
	}

	@Operation(summary = "Get an OrderLine by Id")
	@GetMapping(PUBLIC + "/{id}")
	public ResponseEntity<OrderLine> getOrderLineById(@PathVariable("id")Long id){
		return new ResponseEntity<>(service.getOrderLineById(id), HttpStatus.OK);
	}

	@Operation(summary = "Delete an OrderLine by Id")
	@DeleteMapping(PUBLIC + "/delete/{id}")
	public Map<String, Boolean> deleteOrderLineById(@PathVariable("id") Long id) {
		service.deleteOrderLine(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
