package com.df2h.lsk.util;

import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
 
@Component
public class PasswordGenerator {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    public static void main(String[] args) {

    	// Define desired password length
        int passwordLength = 10;
        
        new PasswordGenerator().generatePassword(10) ;
        // Generate Secure Password
//        String password = generatePassword(passwordLength);
        
        // Print out password value
//        System.out.println("Secure password: " + password);
    }

    public String generatePassword(int length) {
    	LOGGER .info("length : "+length);
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        LOGGER .info(" Generated Pwd : "+returnValue);
        return new String(returnValue);
    }
}