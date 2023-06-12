package com.tsk.ecommerce.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tsk.ecommerce.entities.auth.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Orders extends AuditEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;

	private Double total;

	private Double costDelivery;

	private Boolean payed;

	private Boolean delivered;
	
	@ManyToOne
	@JoinColumn(name = "user_entity")
	private UserEntity userEntity;
	
	@OneToMany(mappedBy = "order")
	private Collection<OrderLine> orderLines;

	public Orders(UserEntity userEntity, Collection<OrderLine> orderLines) {
		super();
		this.userEntity = userEntity;
		this.orderLines = orderLines;
	}

}
