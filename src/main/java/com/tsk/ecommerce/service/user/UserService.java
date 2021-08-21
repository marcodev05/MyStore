package com.tsk.ecommerce.service.user;

import java.io.IOException;

import com.tsk.ecommerce.entities.auth.UserEntity;

public interface UserService {
	
	public UserEntity register(UserEntity userEntity) throws IOException;
	
	public UserEntity getByUsername(String username);
	
	public UserEntity getByUsernameAndPassword(String username, String password);
	
	public Boolean isUsernameExist(String username);

}
