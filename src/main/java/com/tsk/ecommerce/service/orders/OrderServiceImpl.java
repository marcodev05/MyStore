package com.tsk.ecommerce.service.orders;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Orders;
import com.tsk.ecommerce.entities.Pannier;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.OrdersRepository;
import com.tsk.ecommerce.service.customer.CustomerService;
import com.tsk.ecommerce.service.exception.FormatDataInvalidException;
import com.tsk.ecommerce.service.pannier.PannierService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrdersRepository ordersRepo;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PannierService pannierService;	
	
	
	@Override
	public Orders update(Long id, Orders orders) {
		Orders ord = this.getOrdersById(id);
		Double totalCmd = 0.0;
		
		ord.setDescription(orders.getDescription());

		if (orders.getCustomer() != null) {
			Customer c = customerService.getCustomerByEmail(orders.getCustomer().getEmail());
			ord.setCustomer(c);
		} else
			throw new FormatDataInvalidException("L'information du client est obligatoire");

		if (orders.getPannier() != null) {
			Pannier pan = pannierService.getPannierById(orders.getPannier().getIdPannier());
			ord.setPannier(pan);
			List<OrderLine> lines = (List<OrderLine>) pan.getOrderLines();

			for (OrderLine o : lines) {
				totalCmd = totalCmd + o.getTotal();
			}
			
			ord.setTotal(totalCmd);
		} else
			throw new FormatDataInvalidException("Le panier est vide");

		ord.setDate(new Date());

		return ordersRepo.save(ord);
	}

	
	
	
	@Override
	public List<Orders> findAllOrders() {
		return ordersRepo.findAll();
	}

	
	
	@Override
	public Orders getOrdersById(Long id) {
		return ordersRepo.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Ce numero de commande n'existe pas !"));
	}

	
	
	
	@Override
	public Orders getOrdersByCustomerEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public void deleteOrders(Long id) {
		Orders ord = this.getOrdersById(id);
		
		Pannier pan = ord.getPannier();
		pannierService.deletePannier(pan.getIdPannier());
		
		ordersRepo.delete(ord);
	}




	@Override
	public Orders create(Orders orders) {
		Orders ord = new Orders();
		Double totalCmd = 0.0;
		
		ord.setDescription(orders.getDescription());

		if (orders.getCustomer() != null) {
			Customer c = customerService.getCustomerByEmail(orders.getCustomer().getEmail());
			ord.setCustomer(c);
		} else
			throw new FormatDataInvalidException("L'information du client est obligatoire");

		if (orders.getPannier() != null) {
			Pannier pan = pannierService.getPannierById(orders.getPannier().getIdPannier());
			ord.setPannier(pan);
			List<OrderLine> lines = (List<OrderLine>) pan.getOrderLines();

			for (OrderLine o : lines) {
				totalCmd = totalCmd + o.getTotal();
			}
			
			ord.setTotal(totalCmd);
		} else
			throw new FormatDataInvalidException("Le panier est vide");

		ord.setDate(new Date());

		return ordersRepo.save(ord);
	}

}
