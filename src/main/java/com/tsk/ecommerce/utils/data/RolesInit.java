package com.tsk.ecommerce.utils.data;

import com.tsk.ecommerce.entities.auth.ERole;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.repository.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RolesInit {

    @Autowired
    RoleEntityRepository roleEntityRepository;

    @PostConstruct
    private void  initRoles(){
        roleEntityRepository.save(new RoleEntity(ERole.ROLE_USER));
        roleEntityRepository.save(new RoleEntity(ERole.ROLE_ADMIN));
        roleEntityRepository.save(new RoleEntity(ERole.ROLE_SELLER));
    }
}
