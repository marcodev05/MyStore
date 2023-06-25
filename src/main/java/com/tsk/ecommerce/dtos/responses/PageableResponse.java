package com.tsk.ecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageableResponse<T>{
    private T data;
    private Integer totalPages;
    private Long totalElements;
}
