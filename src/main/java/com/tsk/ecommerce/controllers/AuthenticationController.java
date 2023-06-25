package com.tsk.ecommerce.controllers;

import java.io.IOException;

import javax.validation.Valid;

import com.tsk.ecommerce.services.account.AccountService;
import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.auth.UserEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
public class AuthenticationController {

	private final AccountService accountService;

	public AuthenticationController(AccountService accountService) {
		this.accountService = accountService;
	}

	@Operation(summary = "Add a new user admin")
	@PostMapping("/register/phase-1")
	public ResponseEntity<String> registerUserPhase1(@Valid @RequestBody SignUpRequest request) throws IOException{
		UserEntity u = accountService.registerPhase1(request);
		return new ResponseEntity<>("Enregistrement réussi !", HttpStatus.CREATED);	
	}

	@Operation(summary = "login")
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		return new ResponseEntity<>(accountService.login(loginRequest), HttpStatus.OK);
	}
}
