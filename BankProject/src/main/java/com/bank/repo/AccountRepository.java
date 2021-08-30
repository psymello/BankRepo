package com.bank.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bank.model.Account;
import com.bank.model.Customer;

public interface AccountRepository extends CrudRepository<Account, Long> {
	 
}
