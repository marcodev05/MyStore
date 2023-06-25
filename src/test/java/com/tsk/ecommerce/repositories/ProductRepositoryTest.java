package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    private final ProductRepository productRepository;

    ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @BeforeEach
    void setUp() {
        Product inputProduct = new Product();
        inputProduct.setName("KeyBoard");
        inputProduct.setDescription("AZERTY alignment");
        inputProduct.setPrice(400.0);
        inputProduct.setStock(10);
        productRepository.save(inputProduct);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void whenFindByName_thenReturnProducts(){
       List<Product> products = productRepository.findByNameContains("KeyBoard").get();
        assertTrue(products.size() > 0);
    }

}