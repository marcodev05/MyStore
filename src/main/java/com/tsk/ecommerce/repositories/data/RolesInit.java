package com.tsk.ecommerce.repositories.data;

import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
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
