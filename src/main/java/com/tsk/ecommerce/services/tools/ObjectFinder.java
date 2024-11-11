package com.tsk.ecommerce.services.tools;

import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class ObjectFinder {

    public static <T> T findById(JpaRepository repository, String entity, Object id) {
        if (repository == null || id == null) return null;
        Optional<T> object = repository.findById(id);
        if (object.isEmpty()) {
            throw new ResourceNotFoundException(entity, id.toString());
        }
        return object.get();
    }

}
