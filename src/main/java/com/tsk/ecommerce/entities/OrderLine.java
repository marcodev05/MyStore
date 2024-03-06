package com.tsk.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orderlines")
public class OrderLine implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantity;
	private Double subTotal;
	
	@ManyToOne
	@JoinColumn(name = "sub_product")
	private SubProduct subProduct;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
}
