package com.tsk.ecommerce.dtos.requests;


import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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
}
