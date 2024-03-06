package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.LoginRequestDto;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import org.springframework.validation.BindingResult;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDto request, BindingResult bindingResult);
}
