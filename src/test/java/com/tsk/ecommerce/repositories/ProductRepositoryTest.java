package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void whenFindByName_thenReturnProducts() {
        //given
        Product inputProduct = new Product();
        inputProduct.setName("KeyBoard");
        inputProduct.setDescription("some description");
        productRepository.save(inputProduct);

        //when
        List<Product> products = productRepository.findByNameContains("KeyBoard");

        //then
        assertTrue(!products.isEmpty());
    }

}