package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperService {

    public LoginResponseDTO toLoginResponseDto(UserEntity user, String token){
        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setToken(token);
        response.setRoles((List<ERole>) user.getRoles());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
