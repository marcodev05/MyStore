package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.SignUpRequestDto;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface RegistrationService {
    UserEntity registerPhase1(SignUpRequestDto request);
    UserEntity addRoleToUser(Long idUser, List<ERole> roleNames);
    void addNewRole(ERole name);
}
