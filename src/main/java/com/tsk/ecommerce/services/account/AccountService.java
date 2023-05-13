package com.tsk.ecommerce.services.account;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.exceptions.FormatDataInvalidException;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.validations.UserValidation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class AccountService {

    private final PasswordEncoder passWordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final UserValidation userValidation;
    private final RoleEntityRepository roleEntityRepository;

    public AccountService(PasswordEncoder passWordEncoder,
                          UserEntityRepository userEntityRepository,
                          UserValidation userValidation,
                          RoleEntityRepository roleEntityRepository) {
        this.passWordEncoder = passWordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.userValidation = userValidation;
        this.roleEntityRepository = roleEntityRepository;
    }

    public UserEntity register(SignUpRequest request) throws IOException {
        UserEntity newUser = new UserEntity();
        if (!userValidation.isUsernameExisted(request.getUsername())) {
            newUser.setUsername(request.getUsername());
        } else throw new FormatDataInvalidException("User already used");

        if (userValidation.isEmailExisted(request.getEmail())) throw new FormatDataInvalidException("Email already used");
        else {
            newUser.setEmail(request.getEmail());
        }

        RoleEntity role = roleEntityRepository.findByName(ERole.ROLE_USER).get();
        newUser.setRoles(Collections.singletonList(role));
        newUser.setPassword(passWordEncoder.encode(request.getPassword()));
        return userEntityRepository.save(newUser);
    }

    public UserEntity getByUsernameAndPassword(String username, String password) {
        UserEntity user = this.getByUsername(username);
        if(passWordEncoder.matches(password, user.getPassword())) {
            return user;
        }else throw new ResourceNotFoundException("Echec d'authentification !");
    }

    public UserEntity getByUsername(String username) {
        return userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Echec d'authentification !"));
    }
}
