package com.tsk.ecommerce.services.customer;

import java.io.IOException;
import java.util.List;

import com.tsk.ecommerce.dtos.requests.CustomerRequest;
import com.tsk.ecommerce.entities.Customer;

public interface CustomerService {
	
	Customer create(CustomerRequest customer) throws IOException;
	Customer update(Long id, CustomerRequest customer) throws IOException;
	List<Customer> findAllCustomer();
	Customer getCustomerById(Long id);
	Customer getCustomerByEmail(String email);
	void deleteCustomer(Long id);
}
