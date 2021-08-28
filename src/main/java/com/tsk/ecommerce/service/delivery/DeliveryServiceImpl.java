package com.tsk.ecommerce.service.delivery;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Delivery;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Orders;
import com.tsk.ecommerce.entities.Pannier;
import com.tsk.ecommerce.repository.DeliveryRepository;
import com.tsk.ecommerce.service.orders.OrderService;
import com.tsk.ecommerce.service.pannier.PannierService;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	PannierService pannierService;
	
	@Autowired
	DeliveryRepository deliveryRepository;

	



	
	
//	
//	private void calculDeliveryCost(Delivery delivery, Orders order) {
//		
//		Double orderTotal = order.getTotal();
//
//		if (order.getCustomer().getAddress().getCity().toLowerCase().equals("fianarantsoa")) {
//
//			delivery.setCost(1500.0);
//
//		} else {
//
//			if (orderTotal <= 50000)
//				delivery.setCost(3000.0);
//			
//			else if (orderTotal > 50000 && orderTotal <= 100000)
//				delivery.setCost(4000.0);
//			
//			else if (orderTotal > 100000 && orderTotal <= 300000)
//				delivery.setCost(5000.0);
//			
//			else if (orderTotal > 300000 && orderTotal <= 700000)
//				delivery.setCost(7000.0);
//			
//			else if (orderTotal > 700000 && orderTotal <= 1200000)
//				delivery.setCost(8000.0);
//			
//			else if (orderTotal > 1200000 && orderTotal <= 1800000)
//				delivery.setCost(10000.0);
//			
//			else if (orderTotal > 1800000)
//				delivery.setCost(20000.0);
//
//		}
//	}




	@Override
	public Delivery getDeliveryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
