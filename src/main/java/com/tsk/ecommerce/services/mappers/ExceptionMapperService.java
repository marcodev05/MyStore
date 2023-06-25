package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.responses.ExceptionResponseDTO;
import com.tsk.ecommerce.dtos.responses.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExceptionMapperService {

    public ExceptionResponseDTO toResponseDTO(RuntimeException exception,HttpStatus status, HttpServletRequest request){
        ExceptionResponseDTO response = new ExceptionResponseDTO();
        response.setError(status.getReasonPhrase());
        response.setStatus(status.value());
        response.setMessage(exception.getMessage());
        response.setPath(request.getRequestURI());

        LocalDateTime timestamp = LocalDateTime.now();
        response.setTimestamp(timestamp);
        return response;
    }

    public ExceptionResponseDTO toResponseDTO(MethodArgumentNotValidException exception, HttpStatus status){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setError(status.getReasonPhrase());
        response.setStatus(status.value());
        response.setMessage("Invalid Argument");
        response.setPath("");
        response.setFields(errors);

        LocalDateTime timestamp = LocalDateTime.now();
        response.setTimestamp(timestamp);

        return response;
    }

    public ExceptionResponseDTO toResponseDTO(HttpMessageNotReadableException exception, HttpStatus status){
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setError(status.getReasonPhrase());
        response.setStatus(status.value());
        response.setMessage("Not readable exception!");
        response.setPath("");

        LocalDateTime timestamp = LocalDateTime.now();
        response.setTimestamp(timestamp);

        return response;
    }

    public ExceptionResponseDTO toResponseDTO(BindException exception, HttpStatus status){
        ValidationErrorResponse response = new ValidationErrorResponse();
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });


        response.setError(status.getReasonPhrase());
        response.setStatus(status.value());
        response.setMessage(exception.getMessage());
        response.setPath("");
        response.setFields(errors);
        LocalDateTime timestamp = LocalDateTime.now();
        response.setTimestamp(timestamp);

        return response;
    }
}
