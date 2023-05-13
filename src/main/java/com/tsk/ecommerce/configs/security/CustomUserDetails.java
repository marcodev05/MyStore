package com.tsk.ecommerce.configs.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tsk.ecommerce.entities.auth.UserEntity;

public class CustomUserDetails implements UserDetails {
	
	private String username;
	private String password;
	Collection<? extends GrantedAuthority> granteAuthority;

	public static CustomUserDetails fromUserEntityToUserDetails(UserEntity userEntity) {
		CustomUserDetails c = new CustomUserDetails();
		c.username = userEntity.getUsername();
		c.password = userEntity.getPassword();
		List<SimpleGrantedAuthority> simpleGrantedAuthorityCollections = userEntity.getRoles().stream()
				.map((roleEntity) -> new SimpleGrantedAuthority(roleEntity.getName().toString()))
				.collect(Collectors.toList());
		c.granteAuthority = simpleGrantedAuthorityCollections;
		return c;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return granteAuthority;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
