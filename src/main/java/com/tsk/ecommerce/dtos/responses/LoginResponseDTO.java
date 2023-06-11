package com.tsk.ecommerce.dtos.responses;

import com.tsk.ecommerce.entities.enumerations.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
	private Long userId;
	private String username;
	private String email;
	private String token;
	private List<ERole> roles;
	private Date createdAt;
}
