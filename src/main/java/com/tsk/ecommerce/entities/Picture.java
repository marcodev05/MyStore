package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pictures")
public class Picture implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String link;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Picture picture = (Picture) o;
		return Objects.equals(name, picture.name) && Objects.equals(link, picture.link);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, link);
	}
}
