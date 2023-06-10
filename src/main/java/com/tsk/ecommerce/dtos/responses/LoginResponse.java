package com.tsk.ecommerce.dtos.responses;

import com.tsk.ecommerce.entities.enumerations.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class LoginResponse {
	
	private String token;
	private List<ERole> roles;
}
