package com.tsk.ecommerce.controllers;

import com.tsk.ecommerce.entities.RoleEntity;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.tools.ObjectFinder;
import com.tsk.ecommerce.services.user.UserDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

//@TestConfiguration
public class TestConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleRepository;
   // @Autowired
    private UserDataInitializer userDataInitializer;

    @Bean
    public CommandLineRunner initializeTestData() {
        return args -> {
            userDataInitializer.initializeRole();
            initialiseSimpleUser(userEntityRepository);
        };
    }

    public void initialiseSimpleUser(UserEntityRepository userEntityRepository) {
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        user.setPassword(passwordEncoder.encode("testPassword"));
        user.setEmail("test@email.com");
        user.setFirstname("Test");
        user.setLastname("User");
        UserEntity savedUser = userEntityRepository.save(user);
        addRoleToUser(savedUser.getId(), List.of(ERole.ROLE_USER, ERole.ROLE_ADMIN));
    }

    public void addRoleToUser(Long idUser, List<ERole> roleNames) {
        UserEntity userEntity = ObjectFinder.findById(userEntityRepository, "user", idUser);
        List<RoleEntity> roles = roleRepository.findByNameIn(roleNames);
        userEntity.getRoles().addAll(roles);
        userEntityRepository.save(userEntity);
    }

}
