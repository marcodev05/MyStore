package com.tsk.ecommerce.services.user;


import com.tsk.ecommerce.dtos.requests.SignUpRequest;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.validators.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Mock
    private UserEntityRepository userEntityRepository;
    @Mock
    private UserValidator userValidator;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private RegistrationService registrationService;


    @Test
    void registerPhase1() {
        //given
        SignUpRequest request = new SignUpRequest("toto", "toto@toto.com", "seller1234");
        BindingResult bindingResult = mock(BindingResult.class);
        Mockito.when(userEntityRepository.save(any(UserEntity.class))).thenReturn(getUserEntity(true));

        //when
        UserEntity user = registrationService.registerPhase1(request, bindingResult);

        //then
        verify(userValidator).validateSignUp(request);
        assertNotNull(user);
        assertEquals(1L, user.getUserId());
        assertEquals("toto", user.getUsername());
    }

    private static UserEntity getUserEntity(boolean isSaved) {
        final Long id = isSaved ? 1L : null;
        return new UserEntity(id, "toto", "seller1234", "toto@toto.com", "toto", "test", List.of(ERole.ROLE_USER));
    }
}