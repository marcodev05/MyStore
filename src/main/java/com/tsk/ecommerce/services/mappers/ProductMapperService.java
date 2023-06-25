package com.tsk.ecommerce.services.mappers;

import com.tsk.ecommerce.dtos.responses.ProductResponseDTO;
import com.tsk.ecommerce.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperService {

    public ProductResponseDTO toResponseDto(Product product){
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setStock(product.getStock());
        response.setPrice(product.getPrice());
        response.setRating(product.getRating());
        response.setPictures(product.getPictures());
        response.setCreatedAt(product.getCreatedAt());
        return response;
    }
}
