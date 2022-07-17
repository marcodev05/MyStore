package com.tsk.ecommerce.repository;

import com.tsk.ecommerce.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setIdProduct(1L);
        product.setNameProduct("KeyBoard");
        product.setDescription("AZERTY alignment");
        product.setPrice(400.0);
        product.setStock(10);

        testEntityManager.persist(product);
    }


    @Test
    public void repoTest(){

        assertTrue(true);
    }

    @Test
    public void whenFindByName_thenReturnProducts(){
        Optional<List<Product>> products = productRepository.findByNameProductContains("KeyBoard");
        assertTrue(products.isPresent());
    }

    @Test
    @Disabled
    public void whenFindById_thenReturnProducts(){
        productRepository.findById(1L).get();
    }


}