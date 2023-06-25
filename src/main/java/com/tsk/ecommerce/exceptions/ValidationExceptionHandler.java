package com.tsk.ecommerce.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tsk.ecommerce.services.mappers.ExceptionMapperService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionMapperService exceptionMapperService;

    public ValidationExceptionHandler(ExceptionMapperService exceptionMapperService) {
        this.exceptionMapperService = exceptionMapperService;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.BAD_REQUEST));
    }


}
