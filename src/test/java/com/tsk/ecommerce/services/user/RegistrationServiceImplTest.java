package com.tsk.ecommerce.services.user;


import com.tsk.ecommerce.dtos.requests.SignUpRequestDto;
import com.tsk.ecommerce.entities.RoleEntity;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.user.impl.RegistrationServiceImpl;
import com.tsk.ecommerce.services.validators.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {
    @Mock
    private UserEntityRepository userEntityRepository;
    @Mock
    private UserValidator userValidator;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleEntityRepository roleRepository;
    @InjectMocks
    private RegistrationServiceImpl registrationServiceImpl;

    @Test
    void registerPhase1() {
        //given
        SignUpRequestDto request = new SignUpRequestDto("toto", "toto@toto.com", "test1234");
        BindingResult bindingResult = mock(BindingResult.class);
        UserEntity userEntity = getUserEntity();
        UserEntity userMock = mock(UserEntity.class);

        Mockito.when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        Mockito.when(userEntityRepository.findById(1L)).thenReturn(Optional.of(userMock));
        Mockito.when(userMock.getRoles()).thenReturn(new ArrayList<>());
        Mockito.when(roleRepository.findByNameIn(List.of(ERole.ROLE_USER))).thenReturn(getRoleEntities());

        //when
        UserEntity user = registrationServiceImpl.registerPhase1(request, bindingResult);

        //then
        verify(userValidator).validateSignUp(request);
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("toto", user.getUsername());
    }

    private static UserEntity getUserEntity() {
        return new UserEntity(1L, "toto", "test1234", "toto@toto.com", "toto", "test", null);
    }

    private List<RoleEntity> getRoleEntities(){
        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(new RoleEntity(ERole.ROLE_USER));
        return roleEntities;
    }
}