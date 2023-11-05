package com.tsk.ecommerce.dtos.responses;

import org.springframework.http.HttpStatus;

public class ResponseFactory {

    public static <T> Response<T> success(T data){
        return new Response<T>(data).setStatus(HttpStatus.OK.value()).setMessage("Request executed successfully")
                .setError(false);
    }

    public static <T> Response<T> created(T data){
        return new Response<T>(data).setStatus(HttpStatus.CREATED.value()).setMessage("Created successfully")
                .setError(false);
    }

    public static <T> Response<T> notFound(T data){
        return new Response<T>(data).setStatus(HttpStatus.NOT_FOUND.value()).setMessage("Resource not found")
                .setError(true);
    }

    public static <T> Response<T> badRequest(T data){
        return new Response<T>(data).setStatus(HttpStatus.BAD_REQUEST.value()).setMessage("Bad request")
                .setError(true);
    }

    public static <T> Response<T> forbidden(T data){
        return new Response<T>(data).setStatus(HttpStatus.FORBIDDEN.value()).setMessage("Access denied")
                .setError(true);
    }

    public static <T> Response<T> unauthorized(T data){
        return new Response<T>(data).setStatus(HttpStatus.UNAUTHORIZED.value()).setMessage("Unauthorized request")
                .setError(true);
    }
}
