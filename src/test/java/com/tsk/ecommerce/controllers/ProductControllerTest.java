package com.tsk.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsk.ecommerce.dtos.requests.products.ProductRequest;
import com.tsk.ecommerce.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static com.tsk.ecommerce.common.ConstantsApp.ADMIN_URL;
import static com.tsk.ecommerce.common.ConstantsApp.PUBLIC_URL;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Product inputProduct = new Product();
        inputProduct.setIdProduct(1L);
        inputProduct.setNameProduct("KeyBoard");
        inputProduct.setDescription("AZERTY alignment");
        inputProduct.setPrice(400.0);
        inputProduct.setStock(10);
    }

    @Test
    public void shouldGetProductById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(PUBLIC_URL + "/products/", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void shouldAddProduct() throws Exception {
        ProductRequest inputProduct = new ProductRequest("KeyBoard", "AZERTY alignment",
                400.0, 10, new ArrayList<>(), null);
        mockMvc.perform(MockMvcRequestBuilders.post(ADMIN_URL + "/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputProduct))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("when product name is null then return bad request exception ")
    public void productNameMustNotBeBlank() throws Exception {
        ProductRequest inputProduct = new ProductRequest(null, "AZERTY alignment",
                400.0, 10, new ArrayList<>(), null);
        mockMvc.perform(MockMvcRequestBuilders.post(ADMIN_URL + "/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputProduct))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void productCanBeDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/v1/products/delete/", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}