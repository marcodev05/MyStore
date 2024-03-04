package com.tsk.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sub_categories")
public class Product extends AuditEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany
    private Collection<Feature> features;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Collection<Picture> pictures;
}
