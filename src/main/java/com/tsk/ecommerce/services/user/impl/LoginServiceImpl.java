package com.tsk.ecommerce.services.user.impl;

import com.tsk.ecommerce.services.security.jwt.JwtProvider;
import com.tsk.ecommerce.dtos.requests.LoginRequestDto;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.services.security.LoginAuthenticationToken;

import com.tsk.ecommerce.services.user.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public LoginResponseDTO login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(new LoginAuthenticationToken(request.getUsername(), request.getPassword()));
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new LoginResponseDTO(userEntity, token);
    }
}
