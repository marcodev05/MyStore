package com.tsk.ecommerce.services.delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Delivery;
import com.tsk.ecommerce.repositories.DeliveryRepository;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

	
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
