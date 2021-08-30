package com.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Customer;
import com.bank.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer getByUsername(String username) {
		return customerRepository.findByUsername(username);
	}
}
