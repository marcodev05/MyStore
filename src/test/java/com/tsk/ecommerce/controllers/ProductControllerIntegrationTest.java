package com.tsk.ecommerce.controllers;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.tsk.ecommerce.common.ConstantsApp.ADMIN_URL;
import static com.tsk.ecommerce.common.ConstantsApp.PUBLIC_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product inputProduct = new Product();
        inputProduct.setId(1L);
        inputProduct.setName("KeyBoard");
        inputProduct.setDescription("AZERTY alignment");
    }

    @Test
    public void shouldGetProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PUBLIC_URL + "/products/", 1L))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testUsername", roles = {"ADMIN"})
    @Transactional
    public void shouldAddProduct() throws Exception {
        Category category = categoryRepository.save(createCategoryData());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "testName");
        params.add("description", "testDescription");
        params.add("code", "TESTCODE");
        params.add("categoryId", String.valueOf(category.getId()));

        mockMvc.perform(MockMvcRequestBuilders.post(ADMIN_URL + "/products")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(params)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("when product name is null then return bad request exception ")
    @WithMockUser(username = "testUsername", roles = {"ADMIN"})
    public void productNameMustNotBeBlank() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("description", "testDescription");
        params.add("code", "TESTCODE");

        mockMvc.perform(MockMvcRequestBuilders.post(ADMIN_URL + "/products")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(params))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(username = "testUsername", roles = {"ADMIN"})
    public void testProductCanBeDelete() throws Exception {
        productRepository.save(createMockProduct());
        mockMvc.perform(MockMvcRequestBuilders.delete(ADMIN_URL + "/products/"+ 1L))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private Category createCategoryData(){
        Category category = new Category();
        category.setCode("TESTCODE");
        category.setDescription("Test_description");
        category.setName("Test_name");
        return category;
    }

    private Product createMockProduct(){
        Product product = new Product();
        product.setCode("ONE");
        product.setName("Product ONE");
        product.setDescription("Product ONE description");
        return product;
    }

}