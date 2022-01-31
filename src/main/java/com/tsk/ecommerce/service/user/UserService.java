package com.tsk.ecommerce.service.user;

import java.io.IOException;

import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.payload.request.SignUpRequest;

public interface UserService {
	
	public UserEntity register(SignUpRequest request) throws IOException;
	
	public UserEntity getByUsername(String username);
	
	public UserEntity getByUsernameAndPassword(String username, String password);
	
	public Boolean isUsernameExist(String username);
	
	public Boolean isEmailExist(String email);
	
	public void delete(Long idUser);

}
