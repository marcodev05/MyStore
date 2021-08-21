package com.tsk.ecommerce.service.customer;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Address;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.CustomerRepository;
import com.tsk.ecommerce.service.address.AddressService;
import com.tsk.ecommerce.service.exception.FormatDataInvalidException;
import com.tsk.ecommerce.utils.email.EmailUtil;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AddressService addressService;
	
	
	
	@Override
	public Customer create(Customer customer) throws IOException {
		Customer c = new Customer();
		if (EmailUtil.isEmailFormat(customer.getEmail())) {
			
			if (this.isEmailExist(customer.getEmail()))
				throw new FormatDataInvalidException("Ce email existe dejà !");
			else {
				c.setEmail(customer.getEmail());
			}
		} else
			throw new FormatDataInvalidException("Email invalid !");

		
		if (customer.getAddress() != null) {
			Address addr = addressService.create(customer.getAddress());
			c.setAddress(addr);
		} else
			throw new FormatDataInvalidException(" L'adresse est vide !");

		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());

		return customerRepository.save(c);
	}

	
	
	
	@Override
	public Customer update(Long id, Customer customer) throws IOException {

		Customer c = this.getCustomerById(id);

		if (EmailUtil.isEmailFormat(customer.getEmail())) {
			c.setEmail(customer.getEmail());
		} else
			throw new FormatDataInvalidException("Email invalid !");

		if (customer.getAddress() != null) {
			Address addr = addressService.create(customer.getAddress());
			c.setAddress(addr);
		} else
			throw new FormatDataInvalidException(" L'adresse est vide !");

		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());

		return customerRepository.save(c);
	}

	
	
	@Override
	public List<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

	
	
	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
									.orElseThrow(() -> new ResourceNotFoundException("L'identifiant du client est non reconnu"));
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


	@Override
	public Boolean isEmailExist(String email) {
		return customerRepository.existsByEmail(email);
	}

}
