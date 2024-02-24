package com.example.demo;

public class StringCalculator{
	
	public static int add(String numbers) {
		if(numbers==null || numbers.length()<=0) {
			return 0;
		}
		String[] numArray = numbers.split(",");
		int sum = 0;
		for(String str: numArray) {
			sum = sum + Integer.parseInt(str.trim());
		}			
		return sum;		
	}

}