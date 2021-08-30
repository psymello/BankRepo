package com.bank.encoder;

import java.util.List;

public class OddSquareSum {
	public int sum(List<Integer> values) {
		int sum=0;
		for(Integer i:values)
		{
			if(i%2!=0)
			{
				sum=sum+i*i;
			}
		}
		return sum;
	}
}
