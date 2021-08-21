package com.tsk.ecommerce.service.orderLine;


import com.tsk.ecommerce.entities.OrderLine;

public interface OrderLineService {
	
	public OrderLine create(OrderLine orderLine);
	
	public OrderLine update(Long id, OrderLine orderLine);
	
	public OrderLine getOrderLineById(Long id);
	
	public void deleteOrderLine(Long id);
}
