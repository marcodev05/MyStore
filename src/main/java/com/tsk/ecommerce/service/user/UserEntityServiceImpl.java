package com.tsk.ecommerce.service.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.exception.ForbiddenException;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.RoleEntityRepository;
import com.tsk.ecommerce.repository.UserEntityRepository;
import com.tsk.ecommerce.exception.FormatDataInvalidException;
import com.tsk.ecommerce.utils.email.EmailUtil;

@Service
@Transactional
public class UserEntityServiceImpl implements UserService {
	
	@Autowired
	RoleEntityRepository roleRepository;
	
	@Autowired
	UserEntityRepository userRepository;
	
	@Autowired
	PasswordEncoder passWordEncoder;

	@Override
	public UserEntity register(UserEntity user) throws IOException {
		UserEntity newUser = new UserEntity();
		
		//String id = UUID.randomUUID().toString();
		
		RoleEntity role = roleRepository.findByName("ROLE_ADMIN");
		newUser.setRole(role);
		
		if(!this.isUsernameExist(user.getUsername())) {
			newUser.setUsername(user.getUsername());
		}else throw new ForbiddenException("Ce nom d'utilisateur existe dejà !");
		
		if (EmailUtil.isEmailFormat(user.getEmail())) {
			
			if (this.isEmailExist(user.getEmail()))
				throw new FormatDataInvalidException("Ce email existe dejà !");
			else {
				newUser.setEmail(user.getEmail());
			}
				
		} else
			throw new FormatDataInvalidException("Email format invalid !");
		
		newUser.setPassword(passWordEncoder.encode(user.getPassword()));
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		
		return userRepository.save(newUser);
	}

	
	

	@Override
	public UserEntity getByUsernameAndPassword(String username, String password) {	
		UserEntity user = this.getByUsername(username);
		if(passWordEncoder.matches(password, user.getPassword())) {
			return user;
		}else throw new ResourceNotFoundException("Echec d'authentification !");
		
	}

	
	
	@Override
	public UserEntity getByUsername(String username) {	
		return userRepository.findByUsername(username)
								.orElseThrow(() -> new ResourceNotFoundException("Echec d'authentification !"));
	}




	@Override
	public Boolean isUsernameExist(String username) {
		return userRepository.existsByUsername(username);
	}



	@Override
	public Boolean isEmailExist(String email) {
		return userRepository.existsByEmail(email);
	}




	@Override
	public void delete(Long idUser) {
		UserEntity u = userRepository.findById(idUser).get();
		userRepository.delete(u);	
	}

}
