package com.bank.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.bank.model.Card;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback
class CardRepositoryTest {
	
	@Autowired
	private CardRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCards() {
		Card card = new Card();
		card.setCustomerId(new Long(1));
		card.setCardNumber("12345656767");
		card.setCardPin("1212");
		
		Card savedCard = repo.save(card);
		
		Card existCard = entityManager.find(Card.class, savedCard.getId());
		
		assertEquals(existCard.getCardNumber(), card.getCardNumber());
	}

}
