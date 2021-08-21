package com.tsk.ecommerce.service.address;

import java.util.List;

import com.tsk.ecommerce.entities.Address;


public interface AddressService {

	public Address create(Address Address);
	
	public Address update(Long id, Address Address);

	public List<String> findAllCity();

	public Address getAddressById(Long id);
	
	public void deleteAddress(Long id);
}
