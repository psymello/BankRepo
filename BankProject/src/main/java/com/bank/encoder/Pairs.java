package com.bank.encoder;

import java.util.Arrays;

public class Pairs {

	public static void main(String[] args) {
		int[] n = {1,3,2,8,7,6,5,9,4};
		Arrays.sort(n);
		int k = 4;
		for(int i=0;i<n.length;i++)
		{
			for(int j=0;j<n.length;j++)
			{
				if(j!=i && n[j]-n[i]==k) {
					System.out.println(n[i]+" and "+n[j]);
				}
			}
		}
	}

}
