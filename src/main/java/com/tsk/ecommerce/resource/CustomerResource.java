package com.tsk.ecommerce.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.model.NotificationMail;
import com.tsk.ecommerce.service.customer.CustomerService;
import com.tsk.ecommerce.utils.email.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin
@RestController
public class CustomerResource {
	
	private static final String ADMIN = "/admin/v1/customers";
	private static final String PUBLIC = "/api/v1/customers";
	
	@Autowired
	CustomerService service;
	
	
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 */
	
	@Operation(summary = "Get all Customers")
	@GetMapping(ADMIN)
	public ResponseEntity<List<Customer>> getAllCustomer(){
		List<Customer> Customers = service.findAllCustomer();
		return new ResponseEntity<>(Customers, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get a Customer by Id")
	@GetMapping(PUBLIC + "/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id")Long id){
		Customer Customer = service.getCustomerById(id);
		return new ResponseEntity<>(Customer, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get a Customer by email")
	@GetMapping(ADMIN + "email/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email")String email){
		Customer Customer = service.getCustomerByEmail(email);
		return new ResponseEntity<>(Customer, HttpStatus.OK);
	}
	
	
	
	
	@Operation(summary = "Add a new Customer")
	@ApiResponse(responseCode = "201", description = "Customer is created")
	@RequestMapping(value=PUBLIC + "/add", method=RequestMethod.POST)
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer Customer) throws IOException {
		Customer c = service.create(Customer);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}

	
	
	@Operation(summary = "Update a Customer by Id")
	@PutMapping(PUBLIC + "/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer Customer, @PathVariable("id") Long id ) throws IOException {
		Customer c = service.update(id, Customer);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "Delete a Customer by Id")
	@DeleteMapping(PUBLIC + "/delete/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteCustomer(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
