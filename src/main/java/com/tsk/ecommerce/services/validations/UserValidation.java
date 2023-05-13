package com.tsk.ecommerce.services.validations;

import com.tsk.ecommerce.repositories.UserEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    private final UserEntityRepository userEntityRepository;

    public UserValidation(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public Boolean isUsernameExisted(String username) {
        return userEntityRepository.existsByUsername(username);
    }

    public Boolean isEmailExisted(String email) {
        return userEntityRepository.existsByEmail(email);
    }

}
