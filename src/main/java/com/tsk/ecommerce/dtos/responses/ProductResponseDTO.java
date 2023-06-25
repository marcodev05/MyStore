package com.tsk.ecommerce.dtos.responses;

import com.tsk.ecommerce.entities.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Integer rating;
    private Collection<Picture> pictures;
    private Date createdAt;
}
