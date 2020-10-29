package com.eg;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptEg {
	public static void main(String[] args) {
			String password = "abcdef";
			String password1="password";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
		String hashedPassword1 = passwordEncoder.encode(password1);
		System.out.println("password is \t"+hashedPassword1);
			System.out.println(hashedPassword);
	  }
}
