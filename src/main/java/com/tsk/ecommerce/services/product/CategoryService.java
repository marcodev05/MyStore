package com.tsk.ecommerce.services.product;

import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.category.CategoryRequestDto;
import com.tsk.ecommerce.dtos.requests.category.CategorySearchDto;
import com.tsk.ecommerce.entities.Category;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CategoryService {
    Category create(CategoryRequestDto category, BindingResult bindingResult);

    Category update(Long id, CategoryRequestDto request, BindingResult bindingResult);

    PaginationResponse<List<Category>> searchCategory(CategorySearchDto params);

    Category getCategoryById(Long id);

    void deleteCategory(Long id);
}
