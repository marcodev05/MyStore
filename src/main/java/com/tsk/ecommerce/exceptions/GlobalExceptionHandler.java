package com.tsk.ecommerce.exceptions;

import com.tsk.ecommerce.services.mappers.ExceptionMapperService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ExceptionMapperService exceptionMapperService;

    public GlobalExceptionHandler(ExceptionMapperService exceptionMapperService) {
        this.exceptionMapperService = exceptionMapperService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleCustomException(ResourceNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.NOT_FOUND, request));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.BAD_REQUEST, request));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.UNAUTHORIZED, request));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.FORBIDDEN, request));
    }

}
