package com.tsk.ecommerce.dtos.requests.category;

import com.tsk.ecommerce.dtos.OrderBy;
import com.tsk.ecommerce.dtos.Pagination;
import lombok.Data;

@Data
public class CategorySearchDto {
    private String name;
    private String code;
    private Pagination pagination = new Pagination();
    private OrderBy orderBy = new OrderBy();
}
