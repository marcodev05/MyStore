package com.tsk.ecommerce.controllers;


import javax.validation.Valid;

import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import com.tsk.ecommerce.services.account.RegistrationService;
import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.services.account.LoginService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class AuthenticationController {

	private final RegistrationService registrationService;
	private final LoginService loginService;

	public AuthenticationController(RegistrationService registrationService, LoginService loginService) {
		this.registrationService = registrationService;
		this.loginService = loginService;
	}

	@Operation(summary = "step 1 register user")
	@PostMapping("/register")
	public Response<UserEntity> registerUserPhase1(@Valid SignUpRequest request){
		return ResponseFactory.success(registrationService.registerPhase1(request));
	}

	@Operation(summary = "login")
	@PostMapping("/login")
	public Response<LoginResponseDTO> login(LoginRequest loginRequest) {
		return ResponseFactory.success(loginService.login(loginRequest));
	}
}
