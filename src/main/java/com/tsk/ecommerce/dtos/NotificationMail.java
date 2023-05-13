package com.tsk.ecommerce.dtos;


import com.tsk.ecommerce.entities.Orders;

public class NotificationMail {

	private Orders order;

	public NotificationMail(Orders order) {
		super();
		this.order = order;
	}


	public NotificationMail() {
		super();
	}


	public Orders getOrder() {
		return order;
	}


	public void setOrder(Orders order) {
		this.order = order;
	}

}
