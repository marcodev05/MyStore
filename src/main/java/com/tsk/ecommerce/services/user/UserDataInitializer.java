package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.SignUpRequestDto;
import com.tsk.ecommerce.entities.UserEntity;

import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.services.user.impl.RegistrationServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataInitializer {

    private final RegistrationServiceImpl registrationServiceImpl;

    public UserDataInitializer(RegistrationServiceImpl registrationServiceImpl) {
        this.registrationServiceImpl = registrationServiceImpl;
    }

    public void initAdminUser(){
        SignUpRequestDto request = new SignUpRequestDto("test-admin", "test-admin@gmail.com", "admin1234");
        UserEntity savedUser = registrationServiceImpl.registerPhase1(request);
        registrationServiceImpl.addRoleToUser(savedUser.getId(), List.of(ERole.ROLE_ADMIN));
    }

    public void initializeRole(){
        registrationServiceImpl.addNewRole(ERole.ROLE_USER);
        registrationServiceImpl.addNewRole(ERole.ROLE_ADMIN);
    }
}
