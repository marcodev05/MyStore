package com.tsk.ecommerce.service.address;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Address;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.AddressRepository;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	
	@Override
	public Address create(Address address) {
		Address addr = new Address();
		addr.setLot(address.getLot());
		addr.setAddrPlus(address.getAddrPlus());
		addr.setCity(address.getCity());
		return addressRepository.save(addr);
	}

	
	@Override
	public Address update(Long id, Address address) {
		Address addr = this.getAddressById(id);
		addr.setLot(address.getLot());
		addr.setAddrPlus(address.getAddrPlus());
		addr.setCity(address.getCity());
		return addressRepository.save(addr);
	}
	

	@Override
	public Address getAddressById(Long id) {
		 return addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Adresse introuvable ! "));
	}
	
	
	@Override
	public void deleteAddress(Long id) {
		Address c = this.getAddressById(id);
		addressRepository.delete(c);	
	}
 
	
	@Override
	public List<String> findAllCity() {
		List<Address> addrs = addressRepository.findAll();
		Stream<Address> addrStream = addrs.stream();
		List<String> city = new ArrayList<String>();
		
		addrStream.forEach((addr) -> {
			city.add(addr.getCity());
		});
		return city;
	}

}
