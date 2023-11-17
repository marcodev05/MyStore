package com.tsk.ecommerce.services.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tsk.ecommerce.entities.UserEntity;

public class CustomUserDetails implements UserDetails {

	private final UserEntity userEntity;

	public CustomUserDetails(UserEntity userEntity) {
		this.userEntity = userEntity;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userEntity.getRoles().stream()
				.map((roleEntity) -> new SimpleGrantedAuthority(roleEntity.name()))
				.collect(Collectors.toList());
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
