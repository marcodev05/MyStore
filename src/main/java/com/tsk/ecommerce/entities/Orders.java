package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@Data
@Entity
public class Orders implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;

	private Double total;

	private Double costDelivery;

	private Boolean payed;

	private Boolean delivered;

	@CreatedDate
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "addresse")
	private Addresse addresse;
	
	@OneToMany(mappedBy = "order")
	private Collection<OrderLine> orderLines;

	public Orders(Collection<OrderLine> orderLines) {
		super();
		this.orderLines = orderLines;
	}

}
