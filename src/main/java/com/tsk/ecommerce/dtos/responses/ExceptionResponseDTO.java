package com.tsk.ecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDTO {

    private Integer status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
