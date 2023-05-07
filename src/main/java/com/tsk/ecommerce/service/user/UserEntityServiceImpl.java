package com.tsk.ecommerce.service.user;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.dto.request.SignUpRequest;
import com.tsk.ecommerce.entities.auth.ERole;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.RoleEntityRepository;
import com.tsk.ecommerce.repository.UserEntityRepository;
import com.tsk.ecommerce.exception.FormatDataInvalidException;

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
		if (!this.isUsernameExisted(request.getUsername())) {
			newUser.setUsername(request.getUsername());
		} else throw new FormatDataInvalidException("User already used");

		if (this.isEmailExisted(request.getEmail())) throw new FormatDataInvalidException("Email already used");
		else {
			newUser.setEmail(request.getEmail());
		}

		this.checkRole(request, newUser);
		newUser.setPassword(passWordEncoder.encode(request.getPassword()));
		return userRepository.save(newUser);
	}

	private void checkRole(SignUpRequest request, UserEntity newUser) {
		if (request.getRole() == null || request.getRole().isEmpty()) {
			RoleEntity role = roleRepository.findByName(ERole.ROLE_USER).get();
			newUser.setRoles(Collections.singletonList(role));
		} else {
			switch (request.getRole().toLowerCase()) {
				case "admin":
					RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					newUser.setRoles(Collections.singletonList(adminRole));
					break;

				case "seller":
					RoleEntity sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					newUser.setRoles(Collections.singletonList(sellerRole));
					break;

				default:
					RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					newUser.setRoles(Collections.singletonList(userRole));
					break;
			}
		}
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
	public Boolean isUsernameExisted(String username) {
		return userRepository.existsByUsername(username);
	}


	@Override
	public Boolean isEmailExisted(String email) {
		return userRepository.existsByEmail(email);
	}


	@Override
	public void delete(Long idUser) {
		UserEntity u = userRepository.findById(idUser).get();
		userRepository.delete(u);	
	}

}
