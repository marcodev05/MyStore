package com.tsk.ecommerce.services.product.impl;

import java.util.ArrayList;
import java.util.List;

import com.tsk.ecommerce.common.StringUtils;
import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.products.ProductSearchDto;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.services.ObjectFinder;
import com.tsk.ecommerce.services.mappers.ProductMapper;
import com.tsk.ecommerce.services.product.ProductService;
import com.tsk.ecommerce.services.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tsk.ecommerce.dtos.requests.products.ProductRequestDto;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.repositories.ProductRepository;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    private static Specification<Product> getProductSearchSpecification(ProductSearchDto request) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isBlank(request.getKeyword())) {
                String keyword = request.getKeyword().toLowerCase();
                Predicate keywordPredicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + keyword + "%")
                );
                predicates.add(keywordPredicate);
            }

            if (request.getCategoryId() != null) {
                Join<Product, Category> categoryJoin = root.join("category");
                Predicate categoryPredicate = criteriaBuilder.equal(categoryJoin.get("id"), request.getCategoryId());
                predicates.add(categoryPredicate);
            }

            if (!StringUtils.isBlank(request.getCode())) {
                Predicate codePredicate = criteriaBuilder.equal(root.get("code"), request.getCode());
                predicates.add(codePredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Product create(ProductRequestDto request, BindingResult bindingResult) {
        //String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FieldValidator.validate(bindingResult);
        Product p = productMapper.toProductEntity(request);
        //todo handle images
        return productRepository.save(p);
    }

    @Override
    public Product update(Long id, UpdateProductRequest productRequest, BindingResult bindingResult) {
        FieldValidator.validate(bindingResult);
        Product p = this.getProductById(id);
        productRequest.commitTo(p);
        //todo handle images
        p.setCategory(ObjectFinder.findById(categoryRepository, "category", productRequest.getCategoryId()));
        return productRepository.save(p);
    }

	@Override
    public PaginationResponse<List<Product>> searchProduct(ProductSearchDto request) {
        Specification<Product> specification = getProductSearchSpecification(request);

        Sort sortBy = Sort.by(request.getOrderBy().isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC, request.getOrderBy().getField());
        Pageable pageable = PageRequest.of(request.getPagination().getPage() - 1, request.getPagination().getSize(), sortBy);

        Page<Product> productPage = productRepository.findAll(specification, pageable);
        return new PaginationResponse<>(productPage.getContent(), productPage.getTotalElements());
    }

    @Override
    public Product getProductById(Long id) {
        return ObjectFinder.findById(productRepository, "product", id);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
