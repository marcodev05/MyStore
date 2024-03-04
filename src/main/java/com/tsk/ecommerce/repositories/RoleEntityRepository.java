package com.tsk.ecommerce.repositories;

import com.tsk.ecommerce.entities.RoleEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findByNameIn(List<ERole> names);
    Optional<RoleEntity> findByName(ERole name);
}
