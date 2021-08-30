package com.bank.encoder;

public class Temp {
	public String longestEvenLengthWord(String input) {
		String splits[] = input.split("[ .,]");
		int lenEven = 0;
		String evenString = "";
		for(String s:splits) {
			if(s.length()%2 == 0) {
				if(s.length() > lenEven) {
					lenEven=s.length();
					evenString=s;
				}
			}
		}
		return evenString;
	}
}
