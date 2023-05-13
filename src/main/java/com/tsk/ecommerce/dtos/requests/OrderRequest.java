package com.tsk.ecommerce.dtos.requests;

import java.util.Collection;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {

	@NotBlank(message = "your order is empty")
	private Collection<OrderlineRequest> orderlineRequests;
	
	@NotBlank(message = "customer information is empty")
	private CustomerRequest customerRequest;
}
