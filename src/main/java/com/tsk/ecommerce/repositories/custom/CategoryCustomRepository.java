package com.tsk.ecommerce.repositories.custom;

import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.category.CategorySearchDto;
import com.tsk.ecommerce.entities.Category;

import java.util.List;

public interface CategoryCustomRepository {
    PaginationResponse<List<Category>> searchCategory(CategorySearchDto params);
}
