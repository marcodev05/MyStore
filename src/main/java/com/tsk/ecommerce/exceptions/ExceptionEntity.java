package com.tsk.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionEntity {
    private String message;
    private String path;

}
