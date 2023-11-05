package com.tsk.ecommerce.services.security;

import com.tsk.ecommerce.exceptions.UnauthorizedException;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tsk.ecommerce.entities.auth.UserEntity;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final UserEntityRepository userEntityRepository;

	public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
		this.userEntityRepository = userEntityRepository;
	}

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userEntityRepository.findByUsername(username);
		if (userEntity.isPresent()) {
			return new CustomUserDetails(userEntity.get());
		}
		throw new UnauthorizedException("Unauthorized request");
	}

}
