package com.tsk.ecommerce.dtos.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryRequest {

    @NotBlank(message = "name category is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
    
    private MultipartFile image;

}
