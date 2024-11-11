package com.tsk.ecommerce.exceptions;

import com.tsk.ecommerce.common.StringUtils;
import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleCustomException(ResourceNotFoundException ex, HttpServletRequest request) {
        String message = ex.getEntityName() != null ? ex.getEntityName() + " " + ex.getEntityId() + " not found. " : ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseFactory.notFound(message));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        ExceptionEntity exceptionEntity = StringUtils.isBlank(ex.getFieldName())
                ? new ExceptionEntity(ex.getMessage(), request.getRequestURI())
                : new ExceptionEntity(ex.getFieldName(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseFactory.badRequest(exceptionEntity));
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

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<Object>> handleValidationException(BindException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            List<String> messages = new ArrayList<>();
            if (errors.containsKey(fieldName)) {
                messages = errors.get(fieldName);
            }
            messages.add(error.getDefaultMessage());
            errors.put(fieldName, messages);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseFactory.badRequest(errors));
    }


  /* @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        System.out.println("method argument not valid exception");
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }*/

}
