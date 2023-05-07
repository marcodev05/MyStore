package com.tsk.ecommerce.repository;

import com.tsk.ecommerce.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product inputProduct = Product.builder()
                .nameProduct("KeyBoard")
                .description("AZERTY alignment")
                .price(400.0)
                .stock(10)
                .build();
        productRepository.save(inputProduct);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void whenFindByName_thenReturnProducts(){
       List<Product> products = productRepository.findByNameProductContains("KeyBoard").get();
        assertTrue(products.size() > 0);
    }

}