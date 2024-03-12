package com.tsk.ecommerce.services.user;

import com.tsk.ecommerce.dtos.requests.LoginRequestDto;
import com.tsk.ecommerce.dtos.responses.LoginResponseDTO;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.services.security.LoginAuthenticationToken;
import com.tsk.ecommerce.services.security.jwt.JwtProvider;
import com.tsk.ecommerce.services.user.impl.LoginServiceImpl;
import com.tsk.ecommerce.shared.MockResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @InjectMocks
    private LoginServiceImpl loginServiceImpl;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtProvider jwtProvider;
    private final UserEntity USER_TOTO = MockResource.createMockUserToto();

    @Test
    void login() {
        //given
        final LoginRequestDto request = new LoginRequestDto("testUsername", "testPassword");

        //config mock
        final BindingResult bindingResult = mock(BindingResult.class);
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(LoginAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(USER_TOTO);

        //when
        LoginResponseDTO responseDTO = loginServiceImpl.login(request);

        //then
        assertNotNull(responseDTO.getUser());
        verify(jwtProvider, times(1)).generateToken(argThat(argument -> argument.equals(USER_TOTO.getUsername())));
    }
}