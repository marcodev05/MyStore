package com.tsk.ecommerce.service.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.dto.request.SignUpRequest;
import com.tsk.ecommerce.entities.auth.ERole;
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
	public UserEntity register(SignUpRequest request) throws IOException {
		UserEntity newUser = new UserEntity();
		
		//String id = UUID.randomUUID().toString();
	
		
		if(!this.isUsernameExist(request.getUsername())) {
			newUser.setUsername(request.getUsername());
		}else throw new ForbiddenException("Ce nom d'utilisateur existe dejà !");
		
		if (EmailUtil.isEmailFormat(request.getEmail())) {
			
			if (this.isEmailExist(request.getEmail()))
				throw new FormatDataInvalidException("Ce email existe dejà !");
			else {
				newUser.setEmail(request.getEmail());
			}
				
		} else
			throw new FormatDataInvalidException("Email format invalid !");
		
		
		
		newUser.setPassword(passWordEncoder.encode(request.getPassword()));
		
		if(request.getRole() == null || request.getRole().isEmpty()) {
			RoleEntity role = roleRepository.findByName(ERole.ROLE_USER)
											.orElseThrow(()-> new ResourceNotFoundException("role is not found.")); 
			newUser.setRole(role);
		}else {
			
			 switch (request.getRole().toLowerCase()) {
		        case "admin":
		          RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		          newUser.setRole(adminRole);
		          break;
		          
		        case "vendor":
		        	RoleEntity vendorRole = roleRepository.findByName(ERole.ROLE_VENDOR)
		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		        	newUser.setRole(vendorRole);
		          break;
		          
		        default:
		        	RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		        	newUser.setRole(userRole);
		          break;
		          
		        }
			
		}
		
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
