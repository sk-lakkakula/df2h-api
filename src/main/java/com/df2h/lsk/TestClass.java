package com.df2h.lsk;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {
public static void main(String[] args) {
	String regex = "^(.+)@(.+)$";
	List <String >emails = new ArrayList<>();
	emails .add("sunil.s@gmail");
	emails .add("test123@yahoo.co.in");
	emails .add("s@");
	Pattern pattern = Pattern.compile(regex);
//	emails = "sunil.s@gmail.com","test123@yahoo.co.in","t@test.com";
	for(String email : emails){
	    Matcher matcher = pattern.matcher(email);
	    System.out.println(email +" : "+ matcher.matches());
	}
}
}
