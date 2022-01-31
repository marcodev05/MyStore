package com.tsk.ecommerce.payload.response;

public class LoginResponse {
	
	private String token;
	private String role;



	public LoginResponse(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}

	public LoginResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
