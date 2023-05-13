package com.tsk.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Picture implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPicture;
	private String link;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;
}
