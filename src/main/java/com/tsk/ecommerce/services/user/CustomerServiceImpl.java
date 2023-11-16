package com.tsk.ecommerce.services.user;

import java.io.IOException;
import java.util.List;

import com.tsk.ecommerce.dtos.requests.CustomerRequest;
import com.tsk.ecommerce.services.account.RegistrationService;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.CustomerRepository;

import com.tsk.ecommerce.services.user.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final RegistrationService registrationService;

	public CustomerServiceImpl(CustomerRepository customerRepository, RegistrationService registrationService) {
		this.customerRepository = customerRepository;
		this.registrationService = registrationService;
	}

	@Override
	public Customer create(CustomerRequest customer) throws IOException {
		Customer c = new Customer();
		c.setEmail(customer.getEmail());
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());
		c.setAddress1(customer.getAddress1());
		c.setAddress2(customer.getAddress2());
		c.setCity(customer.getCity());
		if (customer.getSignUpRequest() != null) {
			registrationService.registerPhase1(customer.getSignUpRequest());
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