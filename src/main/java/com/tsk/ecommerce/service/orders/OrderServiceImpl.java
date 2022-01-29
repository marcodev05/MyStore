package com.tsk.ecommerce.service.orders;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Address;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Orders;
import com.tsk.ecommerce.entities.Pannier;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.model.OrderRequest;
import com.tsk.ecommerce.model.OrderlineRequest;
import com.tsk.ecommerce.repository.OrdersRepository;
import com.tsk.ecommerce.repository.PannierRepository;
import com.tsk.ecommerce.service.address.AddressService;
import com.tsk.ecommerce.service.customer.CustomerService;
import com.tsk.ecommerce.exception.FormatDataInvalidException;
import com.tsk.ecommerce.service.orderLine.OrderLineService;
import com.tsk.ecommerce.service.pannier.PannierService;
import com.tsk.ecommerce.service.product.ProductService;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrdersRepository ordersRepo;
	
	@Autowired
	OrderLineService orderlineService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PannierService pannierService;	
	
	@Autowired
	PannierRepository pannierRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AddressService addressService;
	
	
	@Override
	public Orders update(Long id, Orders orders) {
		Orders ord = this.getOrdersById(id);
		Double totalCmd = 0.0;
		

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
				productService.reduceQtyByOrderLine(o);
			}
			
			ord.setTotal(totalCmd);
		} else
			throw new FormatDataInvalidException("Le panier est vide");

		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date d = new Date();
		ord.setCreatedAt(d);

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
	public Orders create(OrderRequest orderRequest) throws IOException {
		Orders ord = new Orders();
		
		Address addr = new Address(orderRequest.getLot(), orderRequest.getAddrPlus(), orderRequest.getCity());
		Address address = addressService.create(addr);
		
		Customer customer = new Customer(orderRequest.getFirstName(), orderRequest.getLastName(),
										orderRequest.getEmail(), orderRequest.getPhone(), address);
		ord.setCustomer(customerService.create(customer));
		
		Pannier pannier = pannierService.create();
		
		List<OrderLine> orderlines = new ArrayList<OrderLine>();
		for(OrderlineRequest o : orderRequest.getOrderlineRequests()) {
			
			OrderLine orderline = new OrderLine(o.getQte(), o.getProduit(), pannier);
			orderlines.add(orderlineService.create(orderline));
			
		}
		
		pannier.setOrderLines(orderlines);
		
		/*** LIVRAISON ****/
		
		Double subTotal = 0.0;
	
		for (OrderLine o : orderlines) {
			subTotal = subTotal + o.getTotal();	
			productService.reduceQtyByOrderLine(o);
		}
		
		calculCostDelivery(orderRequest, ord, subTotal);
		
		pannier.setSubtotal(subTotal);
		ord.setPannier(pannierRepository.save(pannier));
		
		ord.setCreatedAt(new Date());
		ord.setDelivered(false);
		ord.setPayed(true); //** A MAINTENIR ( atao false io)**/
		
		return ordersRepo.save(ord);
	}




	private void calculCostDelivery(OrderRequest orderRequest, Orders ord, Double subTotal) {
		Double cost = 0.0;
		if (orderRequest.getCity().toLowerCase().equals("fianarantsoa")) {
			cost = 1000.0 ;

		} else {
			
			if (subTotal <= 50000) {cost = 3000.0;}
				
			else if (subTotal > 50000 && subTotal <= 100000) 
				cost = 4000.0;
			
			else if (subTotal > 100000 && subTotal <= 300000)
				cost = 5000.0;
			
			else if (subTotal > 300000 && subTotal <= 700000)
				cost = 7000.0;
			
			else if (subTotal > 700000 && subTotal <= 1200000)
				cost = 8000.0;
			
			else if (subTotal > 1200000 && subTotal <= 1800000)
				cost = 10000.0;
			
			else if (subTotal > 1800000)
				cost = 20000.0;

		}
		
		ord.setCostDelivery(cost);
		
		Double total = subTotal + cost ;
		ord.setTotal(total);
	}



	@Override
	public List<Orders> getAllNewCommands() {
		return ordersRepo.findByPayedTrueAndDeliveredFalse();
	}
	
	
	
	

	


}
