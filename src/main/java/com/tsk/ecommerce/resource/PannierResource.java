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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Pannier;
import com.tsk.ecommerce.service.pannier.PannierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin
@RestController
public class PannierResource {
	
	private static final String PUBLIC = "/api/v1/panniers";
	
	@Autowired
	PannierService service;
	 
	/**
	 * Documentation path
	 * 
	 * http://localhost:8080/swagger-ui.html
	 */
	
//	@Operation(summary = "Create a new Pannier")
//	@ApiResponse(responseCode = "201", description = "Pannier is created")
//	@PostMapping(PUBLIC + "/add")
//	public ResponseEntity<Pannier> addPannier() {
//		Pannier pan = service.create();
//		return new ResponseEntity<>(pan, HttpStatus.CREATED);
//	}
//	
	
	
	@Operation(summary = "Get a Pannier by Id")
	@GetMapping(PUBLIC + "/{id}")
	public ResponseEntity<Pannier> getPannierById(@PathVariable("id") Long id){
		Pannier pan = service.getPannierById(id);
		return new ResponseEntity<>(pan, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get all Order line in pannier")
	@GetMapping(PUBLIC + "/{id}/orderlines")
	public ResponseEntity<List<OrderLine>> getOrderLinesByPannier(@PathVariable("id") Long id){
		List<OrderLine> orderlines = service.getOrderLinesAtPannier(id);
		return new ResponseEntity<>(orderlines, HttpStatus.OK);
	}
	
	
	
//	@Operation(summary = "Update a Pannier by id")
//	@PutMapping("update/{id}")
//	public ResponseEntity<Pannier> updatePannier(@PathVariable("id") Integer id, @RequestBody Pannier Pannier) {
//		Pannier c = service.update(id, Pannier);
//		return new ResponseEntity<>(c, HttpStatus.OK);
//	}
	
	
	@Operation(summary = "Delete a Pannier by Id")
	@DeleteMapping(PUBLIC + "/delete/{id}")
	public Map<String, Boolean> deletePannierById(@PathVariable("id") Long id) {
		service.deletePannier(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}
	

}
