package com.tsk.ecommerce.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {

    @NotBlank(message = "name category is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

}
