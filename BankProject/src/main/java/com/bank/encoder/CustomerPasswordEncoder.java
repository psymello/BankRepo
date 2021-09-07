package com.bank.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomerPasswordEncoder {
	
	public String encodedPassword(String rawPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(rawPassword);
		return encodedPassword;
	}
}
