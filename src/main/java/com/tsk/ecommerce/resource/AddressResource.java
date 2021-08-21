package com.tsk.ecommerce.resource;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.Address;
import com.tsk.ecommerce.service.address.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/address")
public class AddressResource {
	
	@Autowired
	AddressService service;
	
	 
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 */
	
	@Operation(summary = "Add a new Address")
	@ApiResponse(responseCode = "201", description = "Address is created")
	@PostMapping("/add")
	public ResponseEntity<Address> addAddress(@Valid @RequestBody Address Address) {
		Address a = service.create(Address);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Get all city")
	@GetMapping("/city")
	public ResponseEntity<List<String>> getAllCity() {
		List<String> city = service.findAllCity();
		return new ResponseEntity<>(city, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get an Address by Id")
	@GetMapping("/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id) {
		Address addr = service.getAddressById(id);
		return new ResponseEntity<>(addr, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Update an Address")
	@PutMapping("update/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address Address) {
		Address addr = service.update(id, Address);
		return new ResponseEntity<>(addr, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Delete an Address by Id")
	@DeleteMapping("delete/{id}")
	public void deleteAddressById(@PathVariable("id") Long id) {
		service.deleteAddress(id);
	}
	
}
