package com.tsk.ecommerce.services.security;

import com.tsk.ecommerce.exceptions.UnauthorizedException;
import com.tsk.ecommerce.repositories.UserEntityRepository;
import com.tsk.ecommerce.services.i18n.I18nService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tsk.ecommerce.entities.UserEntity;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final UserEntityRepository userEntityRepository;
	private final I18nService i18nService;

	public CustomUserDetailsService(UserEntityRepository userEntityRepository, I18nService i18nService) {
		this.userEntityRepository = userEntityRepository;
		this.i18nService = i18nService;
	}

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userEntityRepository.findByUsername(username);
		if (userEntity.isPresent()) {
			return new CustomUserDetails(userEntity.get());
		}
		throw new UnauthorizedException(i18nService.get("error.access.authorization"));
	}

}
