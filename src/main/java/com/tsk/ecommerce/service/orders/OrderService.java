package com.tsk.ecommerce.service.orders;

import java.io.IOException;
import java.util.List;

import com.tsk.ecommerce.entities.Orders;
import com.tsk.ecommerce.payload.OrderRequest;

public interface OrderService {

	public Orders create(OrderRequest orderRequest) throws IOException;
	
	public Orders update(Long id, Orders order);
	
	public List<Orders> findAllOrders();
	
	public List<Orders> getAllNewCommands();
	
	public Orders getOrdersById(Long id);
	
	public Orders getOrdersByCustomerEmail(String email);
	
	public void deleteOrders(Long id);
	
	
	/*
	 * Quelle est les 10 produits le plus commandé
	 * 
	 */
	
}
