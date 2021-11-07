package com.bairav.urlshortener.converter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


/*
 * Converts the primary key associated with the long URL into a base 62 number.
 * All methods are static.
 */

public class Converter {
	
	//holds all the digits in a base 62 number a-z, followed by A-Z then 0-9
	
	private static List<Character> charIndex; 
	
	
	//converts primary key to a base 62 number
	
	public static String createUniqueId(Long id) {
		ArrayDeque<Integer> base62Digits = convertBase10ToBase62(id); //treat queue as a stack
		Integer index;
		String uniqueUrlId = "";
		
		while((index = base62Digits.poll()) !=null) {  
			
			uniqueUrlId += getCharFromIndex(index); 
			
		}
		
		return uniqueUrlId;
	}
	
	//converts a base 62 number back to the primary key
	
	public static Long getIdFromUniqueKey(String uniqueId) {
		long id = 0L;
		char[] base62Digits = uniqueId.toCharArray();
		int exp = base62Digits.length - 1;
		
		//convert a base 62 number to a base 10
		
		for(int i=0; i < base62Digits.length ; i++) {
			
			int base = getIndexFromChar(base62Digits[i]);
		
			id += base*Math.pow(62.0, exp--);
		}
		
		return id;
	}
	
	//converts a base 10 number to a base 62
	
	private static ArrayDeque<Integer> convertBase10ToBase62(Long id){
		ArrayDeque<Integer> base62Digits = new ArrayDeque<>(); //treat queue as stack
		
		while(id > 0) {
			
			int remainder = (int)(id%62);
			
			base62Digits.push(remainder); 
			
			id /=62;
		}
		
		return base62Digits;
	}
	
	//returns the index to the corresponding character
	
	private static int getIndexFromChar(char digit) {
		return charIndex.indexOf(digit);
	}
	
	//returns the character to the corresponding index
	
	private static char getCharFromIndex(int index) {
		return charIndex.get(index);
	}
	
	//static block to initialize charIndex only once during load
	
	static {
		charIndex = new ArrayList<>();
		
		for(int i= 0; i< 26; i++) {
			char c = (char)('a' + i);
			charIndex.add(c);
		}
		
		for(int i = 0; i< 26; i++) {
			char c = (char)('A' + i);
			charIndex.add(c);
		}
		
		for(int i= 0; i< 10; i++) {
			char c = (char)('0' + i);
			charIndex.add(c);
		}
	}

}

