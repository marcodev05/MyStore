package com.tsk.ecommerce.controllers.auth;

import java.io.IOException;

import javax.validation.Valid;

import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import com.tsk.ecommerce.services.account.AccountService;
import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.services.account.LoginService;

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
	private final LoginService loginService;

	public AuthenticationController(AccountService accountService, LoginService loginService) {
		this.accountService = accountService;
		this.loginService = loginService;
	}

	@Operation(summary = "step 1 register user")
	@PostMapping("/register")
	public ResponseEntity<String> registerUserPhase1(@Valid @RequestBody SignUpRequest request) throws IOException{
		UserEntity u = accountService.registerPhase1(request);
		return new ResponseEntity<>("Enregistrement réussi !", HttpStatus.CREATED);	
	}

	@Operation(summary = "login")
	@PostMapping("/login")
	public Response<LoginResponseDTO> login(LoginRequest loginRequest) {
		return ResponseFactory.success(loginService.login(loginRequest));
	}
}
