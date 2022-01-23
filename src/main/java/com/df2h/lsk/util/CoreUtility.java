package com.df2h.lsk.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CoreUtility {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	public String generateUniqueId(){
		LOGGER.info("Exec ..generateUniqueId ");
		String uniqueNo = null;
		uniqueNo  = UUID.randomUUID().toString();
		LOGGER.info("Unique Number # : "+uniqueNo+", Length : "+uniqueNo.length());
		return uniqueNo;
	}
	
	public static void main(String[] args) {
		CoreUtility coreUtility = new CoreUtility();
		coreUtility .generateUniqueId();
		///		coreUtility .generateTxnCounter();
	}
	
	public boolean isValidEmail(String email){
		LOGGER.info("Exec ..isValidEmail "+email);
		boolean status = false;
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		LOGGER.info("Exec ..isValidEmail "+email);
		status  = matcher.matches();
		LOGGER.info("is Valid Email Result : "+status);
		return status;  
	}
	
	public boolean isValidPhoneNumber(String phNo) 
    { 
		LOGGER.info("is Valid Email Result : "+phNo);
		boolean status = false;
        // The given argument to compile() method  
        // is regular expression. With the help of  
        // regular expression we can validate mobile 
        // number.  
        // 1) Begins with 0 or 91 
        // 2) Then contains 7 or 8 or 9. 
        // 3) Then contains 9 digits 
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}"); 
  
        // Pattern class contains matcher() method 
        // to find matching between given number  
        // and regular expression 
        Matcher m = p.matcher(phNo);
        status = (m.find() && m.group().equals(phNo));
		LOGGER.info("Is Valid Phone Number "+status);
        return status; 
    }
	
	public Date getCurrentDate() {
		java.sql.Date date = null;
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();

		// now, create a java.sql.Date from the java.util.Date
		date = new java.sql.Date(currentDate.getTime());
		return date ;
	}
}
