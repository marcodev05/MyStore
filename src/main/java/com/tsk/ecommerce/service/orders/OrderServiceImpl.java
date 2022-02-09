package com.tsk.ecommerce.service.orders;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.dto.request.OrderRequest;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Orders;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.OrderLineRepository;
import com.tsk.ecommerce.repository.OrdersRepository;
import com.tsk.ecommerce.service.customer.CustomerService;
import com.tsk.ecommerce.service.orderLine.OrderLineService;
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
	ProductService productService;
	
	@Autowired
	OrderLineRepository OrderLineRepository;
	
//	@Override
//	public Orders update(Long id, Orders orders) {
//		Orders ord = this.getOrdersById(id);
//		Double totalCmd = 0.0;
//		
//
//		if (orders.getCustomer() != null) {
//			Customer c = customerService.getCustomerByEmail(orders.getCustomer().getEmail());
//			ord.setCustomer(c);
//		} else
//			throw new FormatDataInvalidException("L'information du client est obligatoire");
//
//		if (orders.getPannier() != null) {
//			Pannier pan = pannierService.getPannierById(orders.getPannier().getIdPannier());
//			ord.setPannier(pan);
//			List<OrderLine> lines = (List<OrderLine>) pan.getOrderLines();
//
//			for (OrderLine o : lines) {
//				totalCmd = totalCmd + o.getTotal();
//				productService.reduceQtyByOrderLine(o);
//			}
//			
//			ord.setTotal(totalCmd);
//		} else
//			throw new FormatDataInvalidException("Le panier est vide");
//
//		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		Date d = new Date();
//		ord.setCreatedAt(d);
//
//		return ordersRepo.save(ord);
//	}

	
	
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
	}


	@Override
	public Orders create(OrderRequest orderRequest) throws IOException {
		Orders ord = new Orders();
		Double total = 0.0;
		
		Customer c = customerService.create(orderRequest.getCustomer());
		ord.setCustomer(c);
		
		Collection<OrderLine> ordlines = new ArrayList<OrderLine>();
		orderRequest.getOrderlines().forEach((o) -> {
			Product p = productService.getProductById(o.getIdProduct());
			OrderLine ol = orderlineService.create(new OrderLine(o.getQuantity(), p));
			ordlines.add(ol);
		});
		ord.setOrderLines(ordlines);

		
		for (OrderLine o : ordlines) {
			total = total + o.getSubTotal();
			productService.reduceQtyByOrderLine(o);
		}
		ord.setTotal(total);

		ord.setCreatedAt(new Date());
		ord.setDelivered(false);
		ord.setPayed(false);

		return ordersRepo.save(ord);
	}



	@Override
	public List<Orders> getAllNewCommands() {
		return ordersRepo.findByPayedTrueAndDeliveredFalse();
	}
	
	
	
	

	


}
