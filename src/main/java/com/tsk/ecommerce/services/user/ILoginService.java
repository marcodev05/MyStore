package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import org.springframework.validation.BindingResult;

public interface ILoginService {
    LoginResponseDTO login(LoginRequest request, BindingResult bindingResult);
}
