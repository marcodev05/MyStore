package com.tsk.ecommerce.service.pannier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Pannier;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.PannierRepository;
import com.tsk.ecommerce.service.orderLine.OrderLineService;

@Service
@Transactional
public class PannierServiceImpl implements PannierService {

	@Autowired
	PannierRepository pannierRepository;
	
	@Autowired
	OrderLineService orderLineService;
	
	@Override
	public Pannier create(List<OrderLine> orderLines) {
		Pannier pannier = new Pannier(orderLines);
		return pannierRepository.save(pannier);
	}
	

	
	@Override
	public void deletePannier(Long id) {
		Pannier pan = this.getPannierById(id);
		List<OrderLine> orderlines =  (List<OrderLine>) pan.getOrderLines();
		for(OrderLine line : orderlines) {
			orderLineService.deleteOrderLine(line.getIdOrderLine());
		}
		pannierRepository.delete(pan);
	}

	
	@Override
	public Pannier getPannierById(Long id) {
		return pannierRepository.findById(id)
									.orElseThrow(() -> new ResourceNotFoundException("Pannier introuvable ! "));
	}




	@Override
	public List<OrderLine> getOrderLinesAtPannier(Long id) {
		
		return pannierRepository.findOrderLineInPannier(id);
	}
	


}
