package com.tsk.ecommerce.repositories.custom;

import com.tsk.ecommerce.dtos.requests.ProductSearchRequest;
import com.tsk.ecommerce.entities.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class ProductCustomRepository {

    private final EntityManager entityManager;

    public ProductCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void search(ProductSearchRequest request){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Product.class);

    }
}
