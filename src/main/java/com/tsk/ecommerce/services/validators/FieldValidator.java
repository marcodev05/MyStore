package com.tsk.ecommerce.services.validators;

import com.tsk.ecommerce.exceptions.ValidationException;
import org.springframework.validation.BindingResult;

public class FieldValidator {
    public static void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}
