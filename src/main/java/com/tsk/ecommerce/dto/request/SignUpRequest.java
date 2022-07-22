package com.tsk.ecommerce.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
	
	  @NotBlank
	  @Size(min = 3, max = 20, message = "username should have 3 letter in min and 20 letters in max")
	  private String username;

	  @NotBlank
	  @Email(message = "invalid email")
	  private String email;

	  private String role;

	  @NotBlank
	  @Size(min = 6, max = 40, message = "password should have 3 letter in min and 20 letters in max")
	  private String password;

	public SignUpRequest(@NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, String role,
			@NotBlank @Size(min = 6, max = 40) String password) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	public SignUpRequest() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	  
	 

}
