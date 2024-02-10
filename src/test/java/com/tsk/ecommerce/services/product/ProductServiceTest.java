package com.tsk.ecommerce.services.product;

import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.repositories.ProductRepository;

import com.tsk.ecommerce.services.mappers.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void getProductById(){
        Long id = 1L;
        //given
        Product inputProduct = new Product();
        inputProduct.setIdProduct(1L);
        inputProduct.setNameProduct("testNameProduct");
        inputProduct.setDescription("testDescription");
        inputProduct.setPrice(400.0);
        inputProduct.setStock(10);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(inputProduct));

        Product found = productService.getProductById(1L);
        Mockito.verify(productRepository).findById(1L);
        assertNotNull(found);
        assertEquals(id, found.getIdProduct());
    }

    @Test
    public void whenProductIdDoesNotExist_then_ResourceNotFoundException() {
        Long id = 2L;
        //given
        Product inputProduct = new Product();
        inputProduct.setIdProduct(1L);
        inputProduct.setNameProduct("testNameProduct");
        inputProduct.setDescription("testDescription");
        inputProduct.setPrice(400.0);
        inputProduct.setStock(10);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->  productService.getProductById(1L));
        String expectedEntity = "product";
        String actualEntity = exception.getEntityName();
        assertTrue(actualEntity.contains(expectedEntity));
    }
}