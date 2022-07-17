package com.tsk.ecommerce.service.product;

import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setIdProduct(1L);
        product.setNameProduct("KeyBoard");
        product.setDescription("AZERTY alignment");
        product.setPrice(400.0);
        product.setStock(10);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    }

    @Test
    public void whenProductIdExist_then_productFound(){
        Long id = 1L;
        Product found = productService.getProductById(id);

        assertEquals(id, found.getIdProduct());
    }
}