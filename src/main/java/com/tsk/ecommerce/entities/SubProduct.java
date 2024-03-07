package com.tsk.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_products", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class SubProduct extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String code;

    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @OneToMany
    private Collection<FeatureValue> featureValues;

    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubProduct subProduct = (SubProduct) o;
        return Objects.equals(name, subProduct.name) && Objects.equals(price, subProduct.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }
}
