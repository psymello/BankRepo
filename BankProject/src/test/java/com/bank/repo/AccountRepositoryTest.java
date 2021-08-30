package com.bank.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.bank.model.Account;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class AccountRepositoryTest {

	@Autowired
	private AccountRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testCreateAccount() {
		Account account = new Account();
		account.setId(new Long(1));
		account.setAccountNumber("12574372346");
		account.setAccountBalance(new Long(25));
		
		Account savedAccount = repo.save(account);
		
		Account existsAccount = entityManager.find(Account.class, savedAccount.getId());
		
		assertEquals(existsAccount.getAccountNumber(), account.getAccountNumber());
	}

}
