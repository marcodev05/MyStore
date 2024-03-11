package com.tsk.ecommerce.services.tools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultObjectFinder implements IObjectFinder{

    @Override
    public <T> T findById(JpaRepository repository, String entity, Object id) {
        return ObjectFinder.findById(repository, entity, id);
    }
}
