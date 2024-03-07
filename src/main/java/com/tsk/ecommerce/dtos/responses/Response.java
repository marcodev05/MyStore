package com.tsk.ecommerce.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Response<T> {
    private T data;
    private String message;
    private int status;
    private boolean error;

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Response<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public boolean isError() {
        return error;
    }

    public Response<T> setError(boolean error) {
        this.error = error;
        return this;
    }
}
