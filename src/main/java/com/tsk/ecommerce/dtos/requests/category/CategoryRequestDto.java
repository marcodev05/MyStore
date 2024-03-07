package com.tsk.ecommerce.dtos.requests.category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "name category is required")
    private String name;

    @NotBlank(message = "code is required")
    @Min(value = 4, message = "Minimum 4 letters")
    private String code;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Image should not be null")
    private MultipartFile image;

}
