package com.tsk.ecommerce.dtos;

import lombok.Data;

@Data
public class OrderBy {
    private String field = "createdAt";
    private boolean ascending = true;

    public OrderBy() {
    }
}
