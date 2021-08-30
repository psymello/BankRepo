package com.bank.encoder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class OddSquareSumTest {

	@Test
	void test() {
		OddSquareSum s=new OddSquareSum();
		List<Integer> list=new ArrayList<>(){{add(1);
											add(2);
											add(3);
											add(4);
											add(5);
											add(6);
											add(7);
											add(8);
											add(9);}};
		int res = s.sum(list);
											
		assertEquals(165, res);
	}

}
