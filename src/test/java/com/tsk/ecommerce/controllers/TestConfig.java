package com.tsk.ecommerce.controllers;

import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@TestConfiguration
public class TestConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Bean
    public CommandLineRunner initializeTestData() {
        return args -> {
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
        user.setRoles(List.of(ERole.ROLE_USER, ERole.ROLE_ADMIN));
        userEntityRepository.save(user);
    }

}
