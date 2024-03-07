package com.tsk.ecommerce.dtos.requests;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, max = 20, message = "username should have 3 letters in min and 20 letters in max")
	private String username;

	@Schema(description = "User email")
	@NotBlank(message = "email is mandatory")
	@Email(message = "Email invalid")
	private String email;

	@NotBlank
	@Size(min = 6, message = "Password should have 6 letters at least")
	private String password;
}
