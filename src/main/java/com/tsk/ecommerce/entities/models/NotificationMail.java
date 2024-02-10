package com.tsk.ecommerce.dtos.forms;


import com.tsk.ecommerce.entities.Orders;
import lombok.Data;

@Data
public class NotificationMail {

	private Orders order;

	public NotificationMail(Orders order) {
		super();
		this.order = order;
	}

	public NotificationMail() {
		super();
	}

}
