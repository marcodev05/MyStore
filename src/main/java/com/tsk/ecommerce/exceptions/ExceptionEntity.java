package com.tsk.ecommerce.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionEntity {
    private String field;
    private String message;
    private String path;

    public ExceptionEntity(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public ExceptionEntity(String field, String message, String path) {
        this.field = field;
        this.message = message;
        this.path = path;
    }
}
