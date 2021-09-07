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
import com.bank.model.SecurityQuestions;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class SecurityQuestionsRepositoryTest {

	@Autowired
	private SecurityQuestionsRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testCreateAccount() {
		SecurityQuestions securityQuestions = new SecurityQuestions();
		securityQuestions.setId(new Long(1));
		securityQuestions.setAns1("one");
		securityQuestions.setAns2("two");
		securityQuestions.setAns3("three");
		
		SecurityQuestions savedQuestions = repo.save(securityQuestions);
		
		SecurityQuestions existsQuestions = entityManager.find(SecurityQuestions.class, savedQuestions.getId());
		
		assertEquals(existsQuestions.getAns1(), securityQuestions.getAns1());
	}

}
