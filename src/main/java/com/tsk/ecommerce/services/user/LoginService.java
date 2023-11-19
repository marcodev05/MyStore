package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.services.security.jwt.JwtProvider;
import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.services.security.LoginAuthenticationToken;

import com.tsk.ecommerce.services.validators.FieldValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public LoginService(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public LoginResponseDTO login(LoginRequest request, BindingResult bindingResult) {
        FieldValidator.validate(bindingResult);
        Authentication authentication = authenticationManager.authenticate(new LoginAuthenticationToken(request.getUsername(), request.getPassword()));
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new LoginResponseDTO(userEntity, token);
    }
}
