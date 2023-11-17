package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "categories")
public class Category extends AuditEntity implements Serializable{
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private String image;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Category category = (Category) o;
		return Objects.equals(name, category.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name);
	}
}
