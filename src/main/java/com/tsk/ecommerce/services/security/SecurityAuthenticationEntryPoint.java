package com.tsk.ecommerce.services.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        final Response<String> data = ResponseFactory.unauthorized("This api required an authentication");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(data.getStatus());
        objectMapper.writeValue(httpServletResponse.getOutputStream(), data);
    }
}
