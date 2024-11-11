package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

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
        userEntityRepository.save(getMockUserToto());

        //when
        Optional<UserEntity> savedUser = userEntityRepository.findByUsername("toto");

        //then
        assertTrue(savedUser.isPresent());
    }

    @Test
    void existsByEmail() {
        // given
        userEntityRepository.save(getMockUserToto());

        //when
        Optional<UserEntity> user = userEntityRepository.findByEmail("toto@toto.com");

        //then
        assertTrue(user.isPresent());
    }

    public static UserEntity getMockUserToto() {
        return new UserEntity(null, "toto", "password", "toto@toto.com", "toto", "test", new ArrayList<>());
    }
}