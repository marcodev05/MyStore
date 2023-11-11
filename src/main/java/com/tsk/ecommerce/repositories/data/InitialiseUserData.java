package com.tsk.ecommerce.repositories.data;

import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.UserEntityRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InitialiseUserData {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialiseUserData(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){
        UserEntity seller = new UserEntity();
        seller.setUsername("seller");
        seller.setRoles(List.of(ERole.ROLE_USER, ERole.ROLE_ADMIN));
        seller.setPassword(passwordEncoder.encode("seller1234"));
        seller.setEmail("seller@gmail.com");
        userEntityRepository.save(seller);
    }
}
