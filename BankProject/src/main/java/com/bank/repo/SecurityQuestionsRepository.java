package com.bank.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.SecurityQuestions;

public interface SecurityQuestionsRepository extends CrudRepository<SecurityQuestions, Long> {
	 
}
