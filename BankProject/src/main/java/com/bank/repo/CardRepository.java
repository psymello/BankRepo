package com.bank.repo;

import org.springframework.data.repository.CrudRepository;

import com.bank.model.Card;

public interface CardRepository extends CrudRepository<Card, Long>{

}
