package com.tsk.ecommerce.dtos.requests;


import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
public class LoginRequest {

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	
	
	public LoginRequest(@NotEmpty String username, @NotEmpty String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
