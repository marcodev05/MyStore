package com.tsk.ecommerce.validators;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.exceptions.BadRequestException;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserEntityRepository userEntityRepository;

    public UserValidator(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public void validateSignUp(SignUpRequest request){
        if (isUsernameExisted(request.getUsername())) throw new BadRequestException("User already used");
        if (isEmailExisted(request.getEmail())) throw new BadRequestException("Email already used");
    }

    public Boolean isUsernameExisted(String username) {
        return userEntityRepository.existsByUsername(username);
    }

    public Boolean isEmailExisted(String email) {
        return userEntityRepository.existsByEmail(email);
    }

}
