package com.tsk.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.ecommerce.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

}
