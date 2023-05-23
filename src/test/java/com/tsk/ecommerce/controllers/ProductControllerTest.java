package com.tsk.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsk.ecommerce.dtos.requests.ProductRequest;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.product.CrudProductService;
import com.tsk.ecommerce.services.product.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc()
class ProductControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;
    @MockBean
    private CrudProductService crudProductService;
    private Product product;

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
        Mockito.when(crudProductService.getProductById(1L)).thenReturn(product);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/",1L))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andDo(MockMvcResultHandlers.print())
                                        .andReturn();
    }


    @Test
    public void shouldAddProduct() throws Exception {
        ProductRequest inputProduct = new ProductRequest("KeyBoard", "AZERTY alignment",
                                                    400.0,10,new ArrayList<>(),null);
        Mockito.when(crudProductService.create(inputProduct)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputProduct))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @DisplayName("when product name is null then return bad request exception ")
    public void productNameMustNotBeBlank() throws Exception {
        ProductRequest inputProduct = new ProductRequest(null, "AZERTY alignment",
                                                        400.0,10,new ArrayList<>(),null);
        Mockito.when(crudProductService.create(inputProduct)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputProduct))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void productCanBeDelete() throws Exception {
        Mockito.doNothing().when(crudProductService).deleteProduct(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/v1/products/delete/", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}