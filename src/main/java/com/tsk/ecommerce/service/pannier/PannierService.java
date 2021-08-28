package com.tsk.ecommerce.service.pannier;

import java.util.Collection;
import java.util.List;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Pannier;

public interface PannierService {

	public Pannier create(List<OrderLine> orderLines);
	
	public void deletePannier(Long id);
	
	public Pannier getPannierById(Long id);
	
	public List<OrderLine> getOrderLinesAtPannier(Long id);
	
}
