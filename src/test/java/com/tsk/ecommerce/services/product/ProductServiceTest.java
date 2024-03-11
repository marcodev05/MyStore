package com.tsk.ecommerce.services.product;

import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.repositories.ProductRepository;

import com.tsk.ecommerce.services.mappers.ProductMapper;
import com.tsk.ecommerce.services.product.impl.ProductServiceImpl;
import com.tsk.ecommerce.services.tools.IObjectFinder;
import com.tsk.ecommerce.shared.MockResource;
import org.junit.jupiter.api.Disabled;
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

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private IObjectFinder objectFinder;
    private final Product PRODUCT_ONE = MockResource.createMockProductOne();

    @Test
    @Disabled
    public void whenProductIdDoesNotExist_ThenResourceNotFoundException() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->  productService.getProductById(1L));
        String actualEntity = exception.getEntityName();
        assertTrue(actualEntity.contains("product"));
    }
}