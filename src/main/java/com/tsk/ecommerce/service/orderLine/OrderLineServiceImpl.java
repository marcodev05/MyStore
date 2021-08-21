package com.tsk.ecommerce.service.orderLine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Pannier;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.OrderLineRepository;
import com.tsk.ecommerce.repository.ProductRepository;
import com.tsk.ecommerce.service.exception.FormatDataInvalidException;
import com.tsk.ecommerce.service.pannier.PannierService;
import com.tsk.ecommerce.service.product.ProductService;

@Service
@Transactional
public class OrderLineServiceImpl implements OrderLineService {

	@Autowired
	OrderLineRepository orderLineRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PannierService pannierService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public OrderLine create(OrderLine orderLine) {
		OrderLine ordln = new OrderLine();
		ordln.setQuantity(orderLine.getQuantity());

		if (orderLine.getProduct() != null) {

			Product p = productService.getProductById(orderLine.getProduct().getIdProduct());
			ordln.setProduct(p);
			Double pu = p.getPrice();

			if (p.getAvailable()) {

				if (p.getStock() >= orderLine.getQuantity()) {

					Integer orderQty = orderLine.getQuantity();
					Double total = pu * orderQty;
					ordln.setTotal(total);

					Integer restStock = p.getStock() - orderLine.getQuantity();
					p.setStock(restStock);
					if (restStock == 0)
						p.setAvailable(false);
					productRepository.save(p);

				} else
					throw new FormatDataInvalidException("Le stock est insuffisant, reste: " + p.getStock());

			} else
				throw new ResourceNotFoundException("Ce produit est indisponible !");

		} else
			throw new FormatDataInvalidException(" Il faut préciser le produit!");

		if (orderLine.getPannier() != null) {
			Pannier pan = pannierService.getPannierById(orderLine.getPannier().getIdPannier());
			ordln.setPannier(pan);
		} else
			throw new FormatDataInvalidException(" Il faut préciser le panier!");

		return orderLineRepository.save(ordln);
	}
	
	
	
	
	@Override
	public OrderLine update(Long id, OrderLine orderLine) {
		
		return null;
	}
	
	
	
	@Override
	public OrderLine getOrderLineById(Long id) {
		return orderLineRepository.findById(id)
									.orElseThrow(() -> new ResourceNotFoundException("Ligne de commande introuvable"));
	}

	
	@Override
	public void deleteOrderLine(Long id) {
		OrderLine ordln = this.getOrderLineById(id);
		Product product = ordln.getProduct();
		Integer stockProduct = product.getStock();
		
		Integer ordQte = ordln.getQuantity();
		
		product.setStock(stockProduct + ordQte);
		if(product.getStock() > 0) {
			product.setAvailable(true);
		} 
		productRepository.save(product);
		
		orderLineRepository.delete(ordln);
	}

}
