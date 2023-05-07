package com.tsk.ecommerce.service.customer;

import java.io.IOException;
import java.util.List;

import com.tsk.ecommerce.dto.request.CustomerRequest;
import com.tsk.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.CustomerRepository;
import com.tsk.ecommerce.exception.FormatDataInvalidException;
import com.tsk.ecommerce.utils.email.EmailUtil;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	UserService userService;

	@Override
	public Customer create(CustomerRequest customer) throws IOException {
		Customer c = Customer.builder()
				.build();
		c.setEmail(customer.getEmail());
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());
		c.setAddress1(customer.getAddress1());
		c.setAddress2(customer.getAddress2());
		c.setCity(customer.getCity());
		if (customer.getSignUpRequest() != null) {
			userService.register(customer.getSignUpRequest());
		}
		return customerRepository.save(c);
	}


	@Override
	public Customer update(Long id, CustomerRequest customer) throws IOException {
		Customer c = this.getCustomerById(id);
		c.setEmail(customer.getEmail());
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());
		c.setAddress1(customer.getAddress1());
		c.setAddress2(customer.getAddress2());
		c.setCity(customer.getCity());
		return customerRepository.save(c);
	}


	@Override
	public List<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

	
	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
									.orElseThrow(() -> new ResourceNotFoundException("L'identifiant est non reconnu"));
	}

	
	@Override
	public void deleteCustomer(Long id) {
		Customer c = this.getCustomerById(id);
		customerRepository.delete(c);
	}


	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email)
							.orElseThrow(() -> new ResourceNotFoundException("Email non reconnu"));
	}

}
