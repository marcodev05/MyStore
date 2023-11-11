package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.auth.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperService {

    public LoginResponseDTO toLoginResponseDto(UserEntity user, String token){
        return new LoginResponseDTO(user, token);
    }
}
