package com.tsk.ecommerce.services.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.repositories.UserEntityRepository;

@Service
@Transactional
public class UserEntityServiceImpl implements UserService {

	
	@Autowired
	UserEntityRepository userRepository;

/*	@Override
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

		RoleEntity role = roleRepository.findByName(ERole.ROLE_USER).get();
		newUser.setRoles(Collections.singletonList(role));
		newUser.setPassword(passWordEncoder.encode(request.getPassword()));
		return userRepository.save(newUser);
	}*/

/*	private void checkRole(SignUpRequest request, UserEntity newUser) {
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
	}*/


	@Override
	public void delete(Long idUser) {
		UserEntity u = userRepository.findById(idUser).get();
		userRepository.delete(u);	
	}

}
