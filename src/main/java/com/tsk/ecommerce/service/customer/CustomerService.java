package com.tsk.ecommerce.service.customer;

import java.io.IOException;
import java.util.List;

import com.tsk.ecommerce.entities.Customer;

public interface CustomerService {
	
	public Customer create(Customer customer) throws IOException;
	
	public Customer update(Long id, Customer customer) throws IOException;
	
	public List<Customer> findAllCustomer();
	
	public Customer getCustomerById(Long id);
	
	public Customer getCustomerByEmail(String email);
	
	public void deleteCustomer(Long id);
	
}
