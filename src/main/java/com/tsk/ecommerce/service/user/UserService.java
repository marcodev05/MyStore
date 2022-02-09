package com.tsk.ecommerce.service.user;

import java.io.IOException;

import com.tsk.ecommerce.dto.request.SignUpRequest;
import com.tsk.ecommerce.entities.auth.UserEntity;

public interface UserService {
	
	public UserEntity register(SignUpRequest request) throws IOException;
	
	public UserEntity getByUsername(String username);
	
	public UserEntity getByUsernameAndPassword(String username, String password);
	
	public Boolean isUsernameExist(String username);
	
	public Boolean isEmailExist(String email);
	
	public void delete(Long idUser);

}
