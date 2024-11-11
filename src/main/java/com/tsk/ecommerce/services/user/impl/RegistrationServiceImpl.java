package com.tsk.ecommerce.services.user.impl;

import com.tsk.ecommerce.dtos.requests.SignUpRequestDto;
import com.tsk.ecommerce.entities.RoleEntity;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.tools.IObjectFinder;
import com.tsk.ecommerce.services.user.RegistrationService;
import com.tsk.ecommerce.services.validators.FieldValidator;
import com.tsk.ecommerce.services.validators.UserValidator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passWordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleRepository;
    private final UserValidator userValidator;
    private final IObjectFinder objectFinder;

    public RegistrationServiceImpl(PasswordEncoder passWordEncoder,
                                   UserEntityRepository userEntityRepository,
                                   RoleEntityRepository roleRepository,
                                   UserValidator userValidator,
                                   IObjectFinder objectFinder) {
        this.passWordEncoder = passWordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.userValidator = userValidator;
        this.objectFinder = objectFinder;
    }

    @Override
    public UserEntity registerPhase1(SignUpRequestDto request) {
        userValidator.validateSignUp(request);
        UserEntity newUser = of(request);
        UserEntity savedUser = userEntityRepository.save(newUser);
        return addRoleToUser(savedUser.getId(), List.of(ERole.ROLE_USER));
    }

    @Override
    public UserEntity addRoleToUser(Long idUser, List<ERole> roleNames) {
        UserEntity userEntity = objectFinder.findById(userEntityRepository, "user", idUser);
        List<RoleEntity> roles = roleRepository.findByNameIn(roleNames);
        userEntity.getRoles().addAll(roles);
        return userEntityRepository.save(userEntity);
    }

    @Override
    public void addNewRole(ERole name) {
        Optional<RoleEntity> isExists = roleRepository.findByName(name);
        if (isExists.isEmpty()) {
            RoleEntity roleEntity = new RoleEntity(name);
            roleRepository.save(roleEntity);
        }
    }

    private UserEntity of(SignUpRequestDto request) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passWordEncoder.encode(request.getPassword()));
        return newUser;
    }
}
