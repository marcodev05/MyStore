package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.validators.FieldValidator;
import com.tsk.ecommerce.services.validators.UserValidator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Collections;

@Service
public class RegistrationService {

    private final PasswordEncoder passWordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final UserValidator userValidator;

    public RegistrationService(PasswordEncoder passWordEncoder,
                               UserEntityRepository userEntityRepository,
                               UserValidator userValidator) {
        this.passWordEncoder = passWordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.userValidator = userValidator;
    }

    public UserEntity registerPhase1(SignUpRequest request, BindingResult bindingResult){
        FieldValidator.validate(bindingResult);
        userValidator.validateSignUp(request);
        UserEntity newUser = of(request);
        return userEntityRepository.save(newUser);
    }

    private UserEntity of(SignUpRequest request) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setRoles(Collections.singletonList(ERole.ROLE_USER));
        newUser.setPassword(passWordEncoder.encode(request.getPassword()));
        return newUser;
    }
}
