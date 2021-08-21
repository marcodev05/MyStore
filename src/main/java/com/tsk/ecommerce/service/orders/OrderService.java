package com.tsk.ecommerce.service.orders;

import java.util.List;

import com.tsk.ecommerce.entities.Orders;

public interface OrderService {

	public Orders create(Orders orders);
	
	public Orders update(Long id, Orders order);
	
	public List<Orders> findAllOrders();
	
	public Orders getOrdersById(Long id);
	
	public Orders getOrdersByCustomerEmail(String email);
	
	public void deleteOrders(Long id);
	
	
	/*
	 * Quelle est les 10 produits le plus commandé
	 * 
	 */
	
}
