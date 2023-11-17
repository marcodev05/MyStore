package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapperService {

    public LoginResponseDTO toLoginResponseDto(UserEntity user, String token){
        return new LoginResponseDTO(user, token);
    }
}
