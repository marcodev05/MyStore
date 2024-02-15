package com.tsk.ecommerce.services.validators;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.exceptions.BadRequestException;
import com.tsk.ecommerce.repositories.UserEntityRepository;

import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    private final UserEntityRepository userEntityRepository;

    public UserValidator(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public void validateSignUp(SignUpRequest request){
        if (isUsernameExisted(request.getUsername())) throw new BadRequestException("Username already used");
        if (isEmailExisted(request.getEmail())) throw new BadRequestException("Email already used");
    }

    public Boolean isUsernameExisted(String username) {
        return userEntityRepository.findByUsername(username).isPresent();
    }

    public Boolean isEmailExisted(String email) {
        return userEntityRepository.findByEmail(email).isPresent();
    }

}
