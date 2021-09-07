package com.bank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.repo.AccountRepository;
import com.bank.repo.CustomerRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public String getAccountNumberById(Long id) {
		Optional<Account> a = accountRepository.findById(id);
		Account acc = a.get();
		return acc.getAccountNumber();
	}
	
	public Long getAccountBalanceById(Long id) {
		Optional<Account> a = accountRepository.findById(id);
		Account acc = a.get();
		return acc.getAccountBalance();
	}
	
	public Account getAccountById(Long id) {
		Optional<Account> a = accountRepository.findById(id);
		Account account = a.get();
		return account;
	}
	
	public String getEmailByCustomerUsername(String username) {
		Customer customer = customerRepository.findByUsername(username);
		Account account = getAccountById(customer.getId());
		return account.getEmail();
	}
}
