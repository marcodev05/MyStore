package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.responses.ExceptionResponseDTO;
import com.tsk.ecommerce.dtos.responses.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExceptionMapperService {

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
}
