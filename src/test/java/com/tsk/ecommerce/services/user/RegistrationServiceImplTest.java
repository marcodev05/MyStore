package com.tsk.ecommerce.services.user;


import com.tsk.ecommerce.dtos.requests.SignUpRequestDto;
import com.tsk.ecommerce.entities.RoleEntity;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.tools.IObjectFinder;
import com.tsk.ecommerce.services.user.impl.RegistrationServiceImpl;
import com.tsk.ecommerce.services.validators.UserValidator;
import com.tsk.ecommerce.shared.MockResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationServiceImpl;
    @Mock
    private UserEntityRepository userEntityRepository;
    @Mock
    private UserValidator userValidator;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleEntityRepository roleRepository;
    @Mock
    private IObjectFinder objectFinder;

    private static final UserEntity USER_TOTO = MockResource.createMockUserToto();
    private static final RoleEntity ROLE_USER = MockResource.createMockRole(ERole.ROLE_USER);

    @Test
    void registerPhase1() {
        //given
        SignUpRequestDto request = new SignUpRequestDto("toto", "toto@toto.com", "test1234");

        when(userEntityRepository.save(any(UserEntity.class))).thenReturn(USER_TOTO);
        when(objectFinder.findById(userEntityRepository, "user", USER_TOTO.getId())).thenReturn(USER_TOTO);
        when(roleRepository.findByNameIn(List.of(ERole.ROLE_USER))).thenReturn(Collections.singletonList(ROLE_USER));

        //when
        registrationServiceImpl.registerPhase1(request);

        //then
        verify(userValidator, times(1)).validateSignUp(request);
        verify(userEntityRepository, times(2)).save(argThat(argument-> argument.getEmail().equals(request.getEmail())));
    }
}