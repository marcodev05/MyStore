package com.tsk.ecommerce.common;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Pagination {
    @Min(1)
    private Integer page;

    @Min(1)
    private Integer size;

    public Pagination() {
        this.page = 1;
        this.size = 10;
    }
}
