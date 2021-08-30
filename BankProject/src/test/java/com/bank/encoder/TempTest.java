package com.bank.encoder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TempTest {

	@Test
	void test1() {
		String testString = "be not afraid of greatness, some are born great, some achieve greatness, "
				+ "and some have greatness thrust upon them.";
		String expectedResult = "afraid";
		Temp t = new Temp();
		String res = t.longestEvenLengthWord(testString);
		
		assertEquals(res, expectedResult);
	}
	
	@Test
	void test2() {
		String testString = "Time to construct great art";
		String expectedResult = "Time";
		Temp t = new Temp();
		String res = t.longestEvenLengthWord(testString);
		
		assertEquals(res, expectedResult);
	}
	
	@Test
	void test3() {
		String testString = "Where are you, stupid.";
		String expectedResult = "stupid";
		Temp t = new Temp();
		String res = t.longestEvenLengthWord(testString);
		
		assertEquals(res, expectedResult);
	}

}
