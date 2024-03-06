package com.tsk.ecommerce.repositories.custom.impl;

import com.tsk.ecommerce.dtos.requests.products.ProductSearchDto;
import com.tsk.ecommerce.entities.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class ProductCustomRepositoryImpl {

    private final EntityManager entityManager;

    public ProductCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void search(ProductSearchDto request){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Product.class);

    }
}
