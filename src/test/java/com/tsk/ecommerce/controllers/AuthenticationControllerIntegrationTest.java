package com.tsk.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.entities.RoleEntity;
import com.tsk.ecommerce.entities.UserEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.repositories.RoleEntityRepository;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
//@Import(TestConfig.class)
class AuthenticationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @BeforeEach
    void setUp() {
        roleEntityRepository.save(createMockRole(ERole.ROLE_USER));
        roleEntityRepository.save(createMockRole(ERole.ROLE_ADMIN));
    }

    @AfterEach
    void tearDown() {
        userEntityRepository.deleteAll();
        roleEntityRepository.deleteAll();
    }

    @Test
    void registerUserPhase1() throws Exception {
        //given
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "testUser1");
        formData.add("password", "testPassword1");
        formData.add("email", "email1@test.com");

        //http request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpect(status().isOk())
                .andReturn();
        Response<UserEntity> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertFalse(response.isError());
    }

    @Test
    void registerUserPhase1_whenEmailIsEmpty_thenBadRequestException() throws Exception {
        //given
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "testUser1");
        formData.add("password", "testPassword1");

        //http request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpect(status().isBadRequest())
                .andReturn();
        Response<UserEntity> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertTrue(response.isError());
    }

    @Test
    void registerUserPhase1_whenEmailAlreadyExists_thenBadRequestException() throws Exception {
        //given
        userEntityRepository.save(getMockUserToto());
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "toto");
        formData.add("password", "testPassword");
        formData.add("email", "toto@toto.com");

        //http request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpect(status().isBadRequest())
                .andReturn();
        Response<UserEntity> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertTrue(response.isError());
    }

    @Test
    void login() throws Exception {
        //given
        userEntityRepository.save(getMockUserToto());
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "toto");
        formData.add("password", "testPassword");

        //http request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpect(status().isOk())
                .andReturn();
    }

    private UserEntity getMockUserToto() {
        return new UserEntity(null, "toto", passwordEncoder.encode("testPassword"), "toto@toto.com", "toto", "test", new ArrayList<>());
    }

    private RoleEntity createMockRole(ERole role) {
        return new RoleEntity(role);
    }

}