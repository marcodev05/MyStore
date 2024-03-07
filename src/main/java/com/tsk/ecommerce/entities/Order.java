package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	@CreatedDate
	private Date createdAt;
}
