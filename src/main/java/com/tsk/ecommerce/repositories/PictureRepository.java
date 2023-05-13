package com.tsk.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

}
