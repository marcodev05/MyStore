package com.tsk.ecommerce.repositories;


import com.tsk.ecommerce.repositories.custom.CategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryCustomRepository {

    Optional<Category> findByCode(String code);
}
