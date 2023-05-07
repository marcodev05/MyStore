package com.tsk.ecommerce.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {

    @NotBlank(message = "name category is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

}
