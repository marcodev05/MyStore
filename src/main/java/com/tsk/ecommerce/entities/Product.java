package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AuditEntity implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;

	private String nameProduct;
	private String description;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private Integer stock;

	private Boolean available;
	private Boolean selected;

	@Max(value = 5)
	@Min(value = 0)
	private Integer rating;

	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private Collection<Picture> pictures;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "product")
	private Collection<OrderLine> orderLines;


}
