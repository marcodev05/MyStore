package com.tsk.ecommerce.services.account;

import com.tsk.ecommerce.services.security.JwtProvider;
import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.services.security.LoginAuthenticationToken;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public LoginService(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public LoginResponseDTO login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new LoginAuthenticationToken(request.getUsername(), request.getPassword()));
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new LoginResponseDTO(userEntity, token);
    }
}
