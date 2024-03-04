package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class UserEntityRepositoryTest {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @AfterEach
    void tearDown() {
        userEntityRepository.deleteAll();
    }

    @Test
    void findByUsername() {
        // given
        UserEntity userEntity = getUserEntity();
        userEntityRepository.save(userEntity);

        //when
        Optional<UserEntity> savedUser = userEntityRepository.findByUsername("toto");

        //then
        assertTrue(savedUser.isPresent());
        assertEquals("toto", savedUser.get().getUsername());
    }

    @Test
    void existsByEmail() {
        // given
        UserEntity userEntity = getUserEntity();
        userEntityRepository.save(userEntity);

        //when
        Optional<UserEntity> user = userEntityRepository.findByEmail("toto@toto.com");

        //then
        assertTrue(user.isPresent());
        assertEquals("toto@toto.com", user.get().getEmail());
    }

    private static UserEntity getUserEntity() {
        return new UserEntity(1L, "toto", "password", "toto@toto.com", "toto", "test", null);
    }
}