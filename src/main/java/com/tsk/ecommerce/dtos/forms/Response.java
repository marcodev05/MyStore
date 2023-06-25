package com.tsk.ecommerce.dtos.forms;

import com.tsk.ecommerce.dtos.responses.ExceptionResponseDTO;

public class Response<T> {
    private T data;
    private String message;
    private Integer status;
    private Integer code;
    private ExceptionResponseDTO errors;

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public ExceptionResponseDTO getErrors() {
        return errors;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Response<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Response<T> setErrors(ExceptionResponseDTO errors) {
        this.errors = errors;
        return this;
    }
}
