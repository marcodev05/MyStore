package com.tsk.ecommerce.controllers.auth;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

import static com.tsk.ecommerce.utils.Constants.PUBLIC_URL;

@CrossOrigin("*")
@RestController
public class UserResource {

	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Operation(summary = "Add a new user admin")
	@PostMapping(PUBLIC_URL + "/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequest request) throws IOException{		
		UserEntity u = userService.register(request);
		return new ResponseEntity<>("Enregistrement réussi !", HttpStatus.CREATED);	
	}
	
	
	
	@Operation(summary = "verify username and passord")
	@PostMapping(PUBLIC_URL + "/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws InvalidKeyException, Exception {
		userService.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		String token = jwtProvider.generateToken(loginRequest.getUsername());
		UserEntity usr = userService.getByUsername(loginRequest.getUsername());
		List<String> roles = usr.getRoles().stream()
				.map((roleEntity) -> {
					return roleEntity.getName().toString();
				}).collect(Collectors.toList());
		LoginResponse response = new LoginResponse(token, roles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
