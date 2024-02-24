package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

	@Test
	void testAdd() {
		assertEquals(0, add(""));
		assertEquals(1, add("1"));
		assertEquals(6, add("1,5"));
		assertEquals(6, addUsingNextLineDelimiter("1\n2,3"));
	    assertEquals(15, addUsingNextLineDelimiter("5\n5,5"));
	    assertEquals(3, addDifferentDelimiters("//;\n1;2"));
	    assertEquals(5, addDifferentDelimiters("//,\n1,2,2"));
	    assertEquals(6, checkForNegativeNumbers("//;\n1;2;3"));
	    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> checkForNegativeNumbers("//;\n1;-2;3"));
        assertEquals("Negative numbers not allowed: -2", exception1.getMessage());

	}
	
	private int addDifferentDelimiters(String numbers) {
		if(numbers==null || numbers.length()<=0) {
			return 0;
		}
		String delimiter = null, numbersPart = null;
		if (numbers.startsWith("//")) {
			delimiter = numbers.substring(2, numbers.indexOf("\n"));
			numbersPart = numbers.substring(numbers.indexOf("\n") + 1);
		}

		String[] numArray = numbersPart.split(delimiter);
		int sum = 0;
		for (String str : numArray) {
			if (!str.isEmpty()) {
				sum += Integer.parseInt(str.trim());
			}
		}
		return sum;
	}

	private int checkForNegativeNumbers(String numbers) {
		if(numbers==null || numbers.length()<=0) {
			return 0;
		}
		String delimiter = null, numbersPart = null;
		StringBuilder negativeNumbers = new StringBuilder();
		if (numbers.startsWith("//")) {
			delimiter = numbers.substring(2, numbers.indexOf("\n"));
			numbersPart = numbers.substring(numbers.indexOf("\n") + 1);
		}
		String[] numArray = null;
		if(numbersPart!=null && !numbersPart.isEmpty())
			numArray = numbersPart.split(delimiter);
		int sum = 0;
		if(numArray!=null && numArray.length>0) {
			for (String str : numArray) {
				if (!str.isEmpty()) {
					if (Integer.parseInt(str.trim()) < 0) {
						if (negativeNumbers.length() > 0) {
							negativeNumbers.append(",");
						}
						negativeNumbers.append(str);
					}else {
						sum += Integer.parseInt(str.trim());
					}
				}
			}
		}
		if (negativeNumbers.length() > 0) {
			throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
		}
		return sum;
	}
	
	int addUsingNextLineDelimiter(String numbers) {
		if(numbers==null || numbers.length()<=0) {
			return 0;
		}
		String[] numArray = numbers.split("[\n,]+");
		int sum = 0;
		for(String str: numArray) {
			if (!str.isEmpty()) {
				sum = sum + Integer.parseInt(str.trim());
			}
		}			
		return sum;		
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
