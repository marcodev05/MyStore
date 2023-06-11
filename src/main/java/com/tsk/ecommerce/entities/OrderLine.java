package com.tsk.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class OrderLine implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrderLine;
	private Integer quantity;
	private Double subTotal;
	
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "id_order")
	private Orders order;
	
}
