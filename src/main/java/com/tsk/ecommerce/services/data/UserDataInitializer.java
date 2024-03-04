package com.tsk.ecommerce.services.data;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.entities.UserEntity;

import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.services.user.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.List;

@Service
public class UserDataInitializer {

    private final RegistrationService registrationService;

    public UserDataInitializer(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void initAdminUser(){
        SignUpRequest request = new SignUpRequest("test-admin", "test-admin@gmail.com", "admin1234");
        UserEntity savedUser = registrationService.registerPhase1(request, new BeanPropertyBindingResult(null, "user"));
        registrationService.addRoleToUser(savedUser.getId(), List.of(ERole.ROLE_ADMIN));
    }

    public void initializeRole(){
        registrationService.addNewRole(ERole.ROLE_USER);
        registrationService.addNewRole(ERole.ROLE_ADMIN);
    }
}
