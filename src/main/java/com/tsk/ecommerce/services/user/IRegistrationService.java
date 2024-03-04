package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IRegistrationService {
    UserEntity registerPhase1(SignUpRequest request, BindingResult bindingResult);
    UserEntity addRoleToUser(Long idUser, List<ERole> roleNames);
    void addNewRole(ERole name);
}
