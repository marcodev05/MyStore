package com.tsk.ecommerce.dtos.responses;

import com.tsk.ecommerce.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
	private UserEntity user;
	private String access_token;
}
