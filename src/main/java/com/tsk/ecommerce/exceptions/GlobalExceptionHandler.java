package com.tsk.ecommerce.exceptions;

import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import com.tsk.ecommerce.services.mappers.ExceptionMapperService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionMapperService exceptionMapperService;

    public GlobalExceptionHandler(ExceptionMapperService exceptionMapperService) {
        this.exceptionMapperService = exceptionMapperService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleCustomException(ResourceNotFoundException ex, HttpServletRequest request) {
        String message = ex.getEntityName() != null ? ex.getEntityName() + " " + ex.getEntityId() + " not found. " : ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseFactory.notFound(message));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseFactory.badRequest(new ExceptionEntity(ex.getMessage(), request.getRequestURI())));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseFactory.unauthorized(new ExceptionEntity(ex.getMessage(), request.getRequestURI())));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ResponseFactory.forbidden(new ExceptionEntity(ex.getMessage(), request.getRequestURI())));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response<Object>> handleValidationException(ValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseFactory.badRequest(errors));
    }

/*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionMapperService.toResponseDTO(ex, HttpStatus.BAD_REQUEST));
    }
*/
}
