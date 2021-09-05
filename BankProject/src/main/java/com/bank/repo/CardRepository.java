package com.bank.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.model.Card;

public interface CardRepository extends CrudRepository<Card, Long>{
	
	List<Card> findByCustomerId(Long customerId);
}
