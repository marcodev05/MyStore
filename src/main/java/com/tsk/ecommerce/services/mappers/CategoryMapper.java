package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.requests.category.CategoryRequestDto;
import com.tsk.ecommerce.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category fromCategoryDto(Category category, CategoryRequestDto request) {
        category.setName(request.getName());
        category.setCode(request.getCode());
        category.setDescription(request.getDescription());
        //BeanUtils.copyProperties();
        return category;
    }
}
