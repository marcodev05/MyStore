package com.tsk.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.auth.RoleEntity;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {

	public RoleEntity findByName(String name);
}
