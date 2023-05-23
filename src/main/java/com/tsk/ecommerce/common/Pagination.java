package com.tsk.ecommerce.common;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Pagination {
    @Min(1)
    private Integer page;

    @Min(1)
    private Integer par_page;

    public Pagination() {
        page = 1;
        par_page = 10;
    }
}
