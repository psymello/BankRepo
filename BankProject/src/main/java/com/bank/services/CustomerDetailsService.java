package com.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bank.model.Customer;
import com.bank.repo.CustomerRepository;
import com.java.details.CustomerDetails;

public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = repo.findByUsername(username);
		if(customer == null) {
			throw new UsernameNotFoundException("Customer not found");
		}
		return new CustomerDetails(customer);
	}

}
