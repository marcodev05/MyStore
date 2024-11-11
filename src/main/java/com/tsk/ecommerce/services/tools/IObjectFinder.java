package com.tsk.ecommerce.services.tools;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IObjectFinder {
    <T> T findById(JpaRepository repository, String entity, Object id);
}
