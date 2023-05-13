package com.tsk.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.enumerations.ERole;
import com.tsk.ecommerce.entities.auth.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {

	public Optional<RoleEntity> findByName(ERole name);
}
