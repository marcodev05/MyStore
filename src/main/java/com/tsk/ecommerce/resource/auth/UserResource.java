package com.tsk.ecommerce.resource.auth;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.config.jwt.JwtProvider;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.model.AuthRequest;
import com.tsk.ecommerce.model.AuthResponse;
import com.tsk.ecommerce.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
public class UserResource {
	
	private static final String ADMIN = "/admin/v1";
	private static final String PUBLIC = "/api/v1";
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Operation(summary = "Add a new user admin")
	@PostMapping(ADMIN + "/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserEntity userEntity) throws IOException{		
		UserEntity u = userService.register(userEntity);
		return new ResponseEntity<>("Enregistrement réussi !", HttpStatus.CREATED);	
	}
	
	
	
	@Operation(summary = "verify username and passord")
	@PostMapping(PUBLIC + "/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest){		
		userService.getByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
		String token = jwtProvider.generateToken(authRequest.getPassword());
		AuthResponse response = new AuthResponse(token);
		return new ResponseEntity<>(response, HttpStatus.CREATED);	
	}
	
	

}
