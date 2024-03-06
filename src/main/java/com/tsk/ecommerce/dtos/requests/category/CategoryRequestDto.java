package com.tsk.ecommerce.dtos.requests.category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "name category is required")
    private String name;

    @NotBlank(message = "code is required")
    private String code;

    @NotBlank(message = "Description is required")
    private String description;

    private MultipartFile image;

}
