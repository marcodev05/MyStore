package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.requests.products.ProductRequestDto;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.services.ObjectFinder;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Product toProductEntity(ProductRequestDto request){
        Product p = new Product();
        p.setDescription(request.getDescription());
        p.setName(request.getName());
        p.setCode(request.getCode());
        p.setCategory(ObjectFinder.findById(categoryRepository, "category", request.getCategoryId()));
        return p;
    }
}
