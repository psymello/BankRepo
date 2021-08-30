package com.bank.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.bank.model.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setUsername("newuser01");
		customer.setPassword("pass10000");
		
		Customer savedCustomer = repo.save(customer);
		
		Customer existCustomer = entityManager.find(Customer.class, savedCustomer.getId());
		
		assertEquals(existCustomer.getUsername(), customer.getUsername());
	}
	
	@Test
	public void testFindCustomerByUsername() {
		String username = "user01";
		Customer customer = repo.findByUsername(username);
		assertNotNull(customer);
	}
	
	@Test
	public void testDeleteCustomerByUsername() {
		String username = "newuser01";
		repo.deleteByUsername(username);
		Customer customer = repo.findByUsername(username);
		assertNull(customer);
	}
}
