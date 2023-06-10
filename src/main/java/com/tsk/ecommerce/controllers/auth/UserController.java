package com.tsk.ecommerce.controllers.auth;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.services.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.configs.jwt.JwtProvider;
import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponse;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.services.user.UserService;

import io.jsonwebtoken.security.InvalidKeyException;
import io.swagger.v3.oas.annotations.Operation;

import static com.tsk.ecommerce.common.ConstantsApp.PUBLIC_URL;

@CrossOrigin("*")
@RestController
public class UserController {

	private final AccountService accountService;
	private final JwtProvider jwtProvider;

	public UserController(AccountService accountService, JwtProvider jwtProvider) {
		this.accountService = accountService;
		this.jwtProvider = jwtProvider;
	}

	@Operation(summary = "Add a new user admin")
	@PostMapping(PUBLIC_URL + "/register")
	public ResponseEntity<String> registerUserPhase1(@Valid @RequestBody SignUpRequest request) throws IOException{
		UserEntity u = accountService.registerPhase1(request);
		return new ResponseEntity<>("Enregistrement réussi !", HttpStatus.CREATED);	
	}

	@Operation(summary = "verify username and passord")
	@PostMapping(PUBLIC_URL + "/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws InvalidKeyException, Exception {
		accountService.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		String token = jwtProvider.generateToken(loginRequest.getUsername());
		UserEntity usr = accountService.getByUsername(loginRequest.getUsername());
		List<ERole> roles = (List<ERole>) usr.getRoles();
		LoginResponse response = new LoginResponse(token, roles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
