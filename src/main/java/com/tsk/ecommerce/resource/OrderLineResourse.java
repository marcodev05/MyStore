package com.tsk.ecommerce.resource;

import java.util.HashMap;
import java.util.List;
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

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.service.orderLine.OrderLineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin
@RestController
public class OrderLineResourse {
	
	private static final String PUBLIC = "/api/v1/orderlines";
	
	@Autowired
	OrderLineService service;
	 
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 */
	
	@Operation(summary = "Add a new OrderLine")
	@ApiResponse(responseCode = "201", description = "OrderLine is created")
	@PostMapping(PUBLIC + "/add")
	public ResponseEntity<OrderLine> addOrderLine(@RequestBody OrderLine orderLine) {
		OrderLine line = service.create(orderLine);
		return new ResponseEntity<>(line, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Get an OrderLine by Id")
	@GetMapping(PUBLIC + "/{id}")
	public ResponseEntity<OrderLine> getOrderLineById(@PathVariable("id")Long id){
		OrderLine ord = service.getOrderLineById(id);
		return new ResponseEntity<>(ord, HttpStatus.OK);
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
