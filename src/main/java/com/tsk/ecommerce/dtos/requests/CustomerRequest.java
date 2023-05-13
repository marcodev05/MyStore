package com.tsk.ecommerce.dtos.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CustomerRequest {

    @NotBlank(message = "Name is required")
    private String firstName;

    private String lastName;

    @Email(message = "email invalid")
    @NotBlank(message = "email is required")
    private String email;

    private String phone;

    @NotBlank(message = "Address is required")
    private String address1;

    private String address2;

    @NotBlank(message = "City is required")
    @NotNull
    private String city;

    private SignUpRequest signUpRequest;
}
