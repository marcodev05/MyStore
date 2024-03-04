package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.entities.RoleEntity;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.ObjectFinder;
import com.tsk.ecommerce.services.validators.FieldValidator;
import com.tsk.ecommerce.services.validators.UserValidator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private final PasswordEncoder passWordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleRepository;
    private final UserValidator userValidator;

    public RegistrationService(PasswordEncoder passWordEncoder,
                               UserEntityRepository userEntityRepository,
                               RoleEntityRepository roleRepository,
                               UserValidator userValidator) {
        this.passWordEncoder = passWordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.userValidator = userValidator;
    }

    public UserEntity registerPhase1(SignUpRequest request, BindingResult bindingResult) {
        FieldValidator.validate(bindingResult);
        userValidator.validateSignUp(request);
        UserEntity newUser = of(request);
        UserEntity savedUser = userEntityRepository.save(newUser);
        return addRoleToUser(savedUser.getId(), List.of(ERole.ROLE_USER));
    }

    private UserEntity of(SignUpRequest request) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passWordEncoder.encode(request.getPassword()));
        return newUser;
    }

    public UserEntity addRoleToUser(Long idUser, List<ERole> roleNames) {
        UserEntity userEntity = ObjectFinder.findById(userEntityRepository, "user", idUser);
        List<RoleEntity> roles = roleRepository.findByNameIn(roleNames);
        userEntity.getRoles().addAll(roles); //how to mock getRoles()
        return userEntityRepository.save(userEntity);
    }

    public void addNewRole(ERole name) {
        Optional<RoleEntity> isExists = roleRepository.findByName(name);
        if (isExists.isEmpty()) {
            RoleEntity roleEntity = new RoleEntity(name);
            roleRepository.save(roleEntity);
        }
    }
}
