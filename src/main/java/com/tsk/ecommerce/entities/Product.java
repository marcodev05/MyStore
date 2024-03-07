package com.tsk.ecommerce.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

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

    @OneToMany(cascade = CascadeType.REMOVE)
    private Collection<Picture> pictures;
}
