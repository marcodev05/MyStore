package com.tsk.ecommerce.controllers.auth;

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
import com.tsk.ecommerce.dto.request.LoginRequest;
import com.tsk.ecommerce.dto.request.SignUpRequest;
import com.tsk.ecommerce.dto.response.LoginResponse;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.service.user.UserService;

import io.jsonwebtoken.security.InvalidKeyException;
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
	@PostMapping(PUBLIC + "/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequest request) throws IOException{		
		UserEntity u = userService.register(request);
		return new ResponseEntity<>("Enregistrement réussi !", HttpStatus.CREATED);	
	}
	
	
	
	@Operation(summary = "verify username and passord")
	@PostMapping(PUBLIC + "/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws InvalidKeyException, Exception{		
		userService.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		String token = jwtProvider.generateToken(loginRequest.getUsername());
		UserEntity usr = userService.getByUsername(loginRequest.getUsername());
		LoginResponse response = new LoginResponse(token, usr.getRole().getName().toString());
		return new ResponseEntity<>(response, HttpStatus.CREATED);	
	}
	
	

}
