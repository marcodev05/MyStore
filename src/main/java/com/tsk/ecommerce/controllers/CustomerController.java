package com.tsk.ecommerce.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsk.ecommerce.dtos.requests.CustomerRequest;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.services.customer.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import static com.tsk.ecommerce.common.ConstantsApp.*;

@RestController
public class CustomerController {

	private final CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}

	/************************** *********** *********************\
	 * 							PUBLIC ROUTES
	 *************************************************************/

	@Operation(summary = "Add a new Customer")
	@ApiResponse(responseCode = "201", description = "Customer is created")
	@PostMapping( PUBLIC_URL + "customers/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest Customer) throws IOException {
		Customer c = service.create(Customer);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}


	/************************** *********** *********************\
	 * 							USER ROUTES
	 *************************************************************/

	@Operation(summary = "Get a Customer by Id")
	@GetMapping(USER_URL + "/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id")Long id){
		Customer Customer = service.getCustomerById(id);
		return new ResponseEntity<>(Customer, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get a Customer by email")
	@GetMapping(USER_URL + "/customers/email/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email")String email){
		Customer Customer = service.getCustomerByEmail(email);
		return new ResponseEntity<>(Customer, HttpStatus.OK);
	}

	
	@Operation(summary = "Update a Customer by Id")
	@PutMapping(USER_URL + "/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerRequest Customer, @PathVariable("id") Long id ) throws IOException {
		Customer c = service.update(id, Customer);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}


	@Operation(summary = "Delete a Customer by Id")
	@DeleteMapping(USER_URL + "/customers/delete/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteCustomer(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}


	/************************** *********** *********************\
	 * 							ADMIN ROUTES
	 *************************************************************/

	@Operation(summary = "Get all Customers")
	@GetMapping(ADMIN_URL + "customers")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		List<Customer> Customers = service.findAllCustomer();
		return new ResponseEntity<>(Customers, HttpStatus.OK);
	}

}
