package com.tsk.ecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class LoginResponse {
	
	private String token;
	private List<String> roles;
}
