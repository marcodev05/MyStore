package com.tsk.ecommerce.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class Product extends AuditEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;

    @OneToMany
    private Collection<Feature> features;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Picture> pictures = new ArrayList<>();

    public List<Picture> removePicture(Picture picture){
        this.getPictures().remove(picture);
        return pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
