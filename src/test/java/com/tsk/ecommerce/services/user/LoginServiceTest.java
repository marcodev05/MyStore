package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.LoginRequest;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.services.security.LoginAuthenticationToken;
import com.tsk.ecommerce.services.security.jwt.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtProvider jwtProvider;
    @InjectMocks
    private LoginService loginService;

    @Test
    void login() {
        //given
        final LoginRequest request = new LoginRequest("testUsername", "testPassword");

        //config mock
        final BindingResult bindingResult = mock(BindingResult.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUsername");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(LoginAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userEntity);
        when(jwtProvider.generateToken("testUsername")).thenReturn("testToken");

        //when
        LoginResponseDTO responseDTO = loginService.login(request, bindingResult);

        //then
        assertNotNull(responseDTO.getUser());
        assertEquals("testUsername", responseDTO.getUser().getUsername());
        assertEquals("testToken", responseDTO.getAccess_token());
    }
}