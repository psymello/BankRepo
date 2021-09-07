package com.bank.repo;

import org.springframework.data.repository.CrudRepository;

import com.bank.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	  //use Class name instead of Table name... here it is 'Customer' not 'customers'
	  //@Query("SELECT c FROM Customer c WHERE c.username = ?1") 
	  Customer findByUsername(String username);
//	  
//	  @Query("DELETE FROM Customer c WHERE c.username = ?1") 
	  void deleteByUsername(String username);
	 
}
