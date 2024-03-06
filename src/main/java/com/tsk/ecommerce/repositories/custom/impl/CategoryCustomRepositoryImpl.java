package com.tsk.ecommerce.repositories.custom.impl;

import com.tsk.ecommerce.common.StringUtils;
import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.category.CategorySearchDto;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.repositories.custom.CategoryCustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
    private final EntityManager em;

    public CategoryCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public PaginationResponse<List<Category>> searchCategory(CategorySearchDto params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = cb.createQuery(Category.class);

        Root<Category> root = criteriaQuery.from(Category.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(params.getName())) {
            Predicate namePredicate = cb.like(root.get("name"), "%" + params.getName().toLowerCase() + "%");
            predicates.add(namePredicate);
        }
        if (!StringUtils.isBlank(params.getCode())) {
            Predicate codePredicate = cb.equal(root.get("code"), params.getCode());
            predicates.add(codePredicate);
        }

        // Predicate orPredicate = cb.or(namePredicate, codePredicate);
        if (!predicates.isEmpty()){
            criteriaQuery.where(
                    cb.or(predicates.toArray(new Predicate[0]))
            );
        }

        TypedQuery<Category> query = em.createQuery(criteriaQuery);

        query.setFirstResult(params.getPagination().getPage() - 1);
        query.setMaxResults(params.getPagination().getSize());

        List<Category> categories = query.getResultList();

        Long total = getTotal(cb, predicates);
        return new PaginationResponse<>(categories, params.getPagination(), total);
    }

    private Long getTotal(CriteriaBuilder cb, List<Predicate> predicates) {
        CriteriaQuery<Long> criteriaCount = cb.createQuery(Long.class);
        criteriaCount.select(cb.count(criteriaCount.from(Category.class)));
        criteriaCount.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(criteriaCount).getSingleResult();
    }

}
