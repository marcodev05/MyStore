package com.tsk.ecommerce.services.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tsk.ecommerce.common.CollectionUtils;
import com.tsk.ecommerce.common.StringUtils;
import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.products.ProductSearchDto;
import com.tsk.ecommerce.dtos.requests.products.UpdateProductRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.models.FileUploaded;
import com.tsk.ecommerce.exceptions.BadRequestException;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.repositories.PictureRepository;
import com.tsk.ecommerce.services.file.FileUploadService;
import com.tsk.ecommerce.services.tools.IObjectFinder;
import com.tsk.ecommerce.services.tools.ObjectFinder;
import com.tsk.ecommerce.services.mappers.ProductMapper;
import com.tsk.ecommerce.services.product.ProductService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final FileUploadService fileUploadService;
    private final PictureRepository pictureRepository;
    private final IObjectFinder objectFinder;

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
    public Product create(ProductRequestDto request) {
        isCodeProductExists(request.getCode());
        Product p = productMapper.toProductEntity(request);
        uploadPictures(request.getFiles(), p);
        return productRepository.save(p);
    }

    @Override
    public Product update(Long id, UpdateProductRequest productRequest) {
        Product p = this.getProductById(id);
        productRequest.commitTo(p);
        p.setCategory(ObjectFinder.findById(categoryRepository, "category", productRequest.getCategoryId()));

        updatePictures(productRequest, p);
        return productRepository.save(p);
    }

    private void updatePictures(UpdateProductRequest productRequest, Product p) {
        if (!CollectionUtils.isEmpty(productRequest.getPictureToDeletes())) {
            p.getPictures().stream()
                    .filter(picture -> productRequest.getPictureToDeletes().contains(picture.getId()))
                    .forEach(picture -> {
                        fileUploadService.delete(picture.getName());
                        pictureRepository.deleteById(picture.getId());
                    });
        }
        uploadPictures(productRequest.getFiles(), p);
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
        return objectFinder.findById(productRepository, "product", id);
    }

    @Override
    public void deleteProduct(Long id) {
        // Check if product is empty
        Product product = objectFinder.findById(productRepository, "product", id);
        product.getPictures().forEach(picture -> {
            fileUploadService.delete(picture.getName());
            pictureRepository.deleteById(picture.getId());
        });
        productRepository.deleteById(id);
    }

    private void isCodeProductExists(String code) {
        Optional<Product> optionalProduct = productRepository.findByCode(code);
        if (optionalProduct.isPresent()) {
            throw new BadRequestException("Code category already exists", "code");
        }
    }

    private void uploadPictures(List<MultipartFile> files, Product product) {
        if (!CollectionUtils.isEmpty(files)) {
            files.forEach(file -> uploadPicture(file, product));
        }
    }

    private void uploadPicture(MultipartFile file, Product product) {
        FileUploaded fileUploaded = fileUploadService.upload(file);
        Picture picture = new Picture(null, fileUploaded.getName(), fileUploaded.getLink());
        picture = pictureRepository.save(picture);
        product.getPictures().add(picture);
    }

}
