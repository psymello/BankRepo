package com.bank.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomerPasswordEncoder {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "hello";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
	}
}
