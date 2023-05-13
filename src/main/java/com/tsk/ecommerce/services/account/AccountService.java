package com.tsk.ecommerce.services.account;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.validators.UserValidator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class AccountService {

    private final PasswordEncoder passWordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final UserValidator userValidator;
    private final RoleEntityRepository roleEntityRepository;

    public AccountService(PasswordEncoder passWordEncoder,
                          UserEntityRepository userEntityRepository,
                          UserValidator userValidator,
                          RoleEntityRepository roleEntityRepository) {
        this.passWordEncoder = passWordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.userValidator = userValidator;
        this.roleEntityRepository = roleEntityRepository;
    }

    public UserEntity registerPhase1(SignUpRequest request) throws IOException {
        userValidator.validateSignUp(request);
        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        RoleEntity role = roleEntityRepository.findByName(ERole.ROLE_USER).get();
        newUser.setRoles(Collections.singletonList(role));
        newUser.setPassword(passWordEncoder.encode(request.getPassword()));
        return userEntityRepository.save(newUser);
    }

    public UserEntity getByUsernameAndPassword(String username, String password) {
        UserEntity user = this.getByUsername(username);
        if(!passWordEncoder.matches(password, user.getPassword())) throw new ResourceNotFoundException("Echec d'authentification !");
        return user;
    }

    public UserEntity getByUsername(String username) {
        return userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Echec d'authentification !"));
    }
}
