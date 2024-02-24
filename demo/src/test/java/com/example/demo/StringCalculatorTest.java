package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

	@Test
	void testAdd() {
		assertEquals(0, add(""));
		assertEquals(1, add("1"));
		assertEquals(6, add("1,5"));
	}
	
	int add(String numbers) {
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
