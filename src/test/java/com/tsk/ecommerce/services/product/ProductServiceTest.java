package com.tsk.ecommerce.services.product;

import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .idProduct(1L)
                .nameProduct("KeyBoard")
                .description("AZERTY alignment")
                .price(400.0)
                .stock(10)
                .build();
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    }

    @Test
    public void whenProductIdExist_then_productFound(){
        Long id = 1L;
        Product found = productService.getProductById(1L);
        Mockito.verify(productRepository).findById(1L);
        assertNotNull(found);
        assertEquals(id, found.getIdProduct());
    }

    @Test
    public void whenProductIdDoesNotExist_then_ResourceNotFoundException(){
        Long id = 2L;
        Exception exception = assertThrows(ResourceNotFoundException.class, ()->{
            Product found = productService.getProductById(2L);
        });
        String expectedMessage = "Le produit est introuvable";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}