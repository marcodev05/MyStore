package com.tsk.ecommerce.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class SignUpRequest {
	
	  @NotBlank
	  @Size(min = 3, max = 20, message = "username should have 3 letters in min and 20 letters in max")
	  private String username;

	  @NotBlank
	  @Email(message = "invalid email")
	  private String email;

	  private String role;

	  @NotBlank
	  @Size(min = 6, message = "password should have 6 letters at least")
	  private String password;

}
