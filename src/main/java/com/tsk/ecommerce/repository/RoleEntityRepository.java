package com.tsk.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.auth.ERole;
import com.tsk.ecommerce.entities.auth.RoleEntity;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {

	public Optional<RoleEntity> findByName(ERole name);
}
