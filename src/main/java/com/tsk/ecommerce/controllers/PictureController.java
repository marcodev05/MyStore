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

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.services.picture.PictureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class PictureController {
	
	private static final String ADMIN = "/admin/v1/pictures";
	private static final String PUBLIC = "/api/v1/pictures";
	private final PictureService service;

	public PictureController(PictureService service) {
		this.service = service;
	}

	@Operation(summary = "Add a new Picture")
	@ApiResponse(responseCode = "201", description = "Picture is appended")
	@PostMapping(ADMIN + "/add")
	public ResponseEntity<Picture> addPicture(@RequestBody Picture picture) {
		return new ResponseEntity<>(service.addPicture(picture), HttpStatus.CREATED);
	}

	@Operation(summary = "Get a Picture by Id")
	@GetMapping(PUBLIC + "/{id}")
	public ResponseEntity<Picture> getPictureById(@PathVariable("id")Long id){
		return new ResponseEntity<>(service.getPictureById(id), HttpStatus.OK);
	}

	@Operation(summary = "Delete a Picture by Id")
	@DeleteMapping(ADMIN + "/{id}/delete")
	public Map<String, Boolean> deletePictureById(@PathVariable("id") Long id) {
		service.deletePicture(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

//	@Operation(summary = "Get all Products by Picture id")
//	@GetMapping("{id}/products")
//	public ResponseEntity<List<Product>> getAllProductsByPicture(@PathVariable("id") Integer idPicture){
//		List<Product> products = service.getAllProductsByPicture(idPicture);
//		return new ResponseEntity<>(products, HttpStatus.OK);
//	}

}
