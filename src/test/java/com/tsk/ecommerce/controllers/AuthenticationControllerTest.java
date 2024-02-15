package com.tsk.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Import(TestConfig.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void TestRegisterUserPhase1() throws Exception {
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
    void TestRegisterUserPhase1_whenEmailIsEmpty_thenBadRequestException() throws Exception {
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
    void TestRegisterUserPhase1_whenEmailAlreadyExists_thenBadRequestException() throws Exception {
        //given
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "testUser");
        formData.add("password", "testPassword");
        formData.add("email", "email@test.com");

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
    void TesLogin() throws Exception {
        //given
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "testUser");
        formData.add("password", "testPassword");

        //http request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpect(status().isOk())
                .andReturn();
    }

}