package com.tsk.ecommerce.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationErrorResponse extends ExceptionResponseDTO{

    private Map<String, String> fields;
}
