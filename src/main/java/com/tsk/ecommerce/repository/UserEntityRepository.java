package com.tsk.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.auth.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

	public Optional<UserEntity> findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
}
