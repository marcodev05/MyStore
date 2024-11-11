package com.tsk.ecommerce.controllers;


import javax.validation.Valid;

import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import com.tsk.ecommerce.services.user.LoginService;
import com.tsk.ecommerce.services.user.RegistrationService;
import com.tsk.ecommerce.dtos.requests.LoginRequestDto;
import com.tsk.ecommerce.dtos.requests.SignUpRequestDto;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.UserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

	private final RegistrationService registrationService;
	private final LoginService loginService;

	@Operation(summary = "step 1 register user")
	@PostMapping("/register")
	public Response<UserEntity> registerUserPhase1(@Valid SignUpRequestDto request){
		return ResponseFactory.success(registrationService.registerPhase1(request));
	}

	@Operation(summary = "login")
	@PostMapping("/login")
	public Response<LoginResponseDTO> login(@Valid LoginRequestDto loginRequestDto) {
		return ResponseFactory.success(loginService.login(loginRequestDto));
	}
}
