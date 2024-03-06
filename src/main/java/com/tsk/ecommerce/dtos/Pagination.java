package com.tsk.ecommerce.dtos;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Pagination {
    @Min(1)
    private Integer page;

    @Min(1)
    private Integer size;

    public Pagination() {
        page = 1;
        size = 10;
    }
}
