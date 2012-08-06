package org.codingexercise.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.HashMap;

import org.codingexercise.BigIntHelper;
import org.junit.Test;

public class BigIntHelperTests {

	@Test
	public void testConvertLongToEnglish() {
		BigIntHelper bigIntHelper = new BigIntHelper();
		
		HashMap<BigInteger, String> testSet = new HashMap<BigInteger, String>();
		testSet.put(BigInteger.ZERO, "zero");
		testSet.put(BigInteger.valueOf(00), "zero");
		testSet.put(BigInteger.valueOf(05), "five");
		testSet.put(BigInteger.valueOf(5), "five");
		testSet.put(BigInteger.valueOf(10), "ten");
		testSet.put(BigInteger.valueOf(19), "nineteen");
		testSet.put(BigInteger.valueOf(20), "twenty");
		testSet.put(BigInteger.valueOf(66), "sixty-six");
		testSet.put(BigInteger.valueOf(99), "ninety-nine");
		testSet.put(BigInteger.valueOf(100), "one hundred");
		testSet.put(BigInteger.valueOf(564), "five hundred sixty-four");
		testSet.put(BigInteger.valueOf(999), "nine hundred ninety-nine");
		testSet.put(BigInteger.valueOf(1000), "one thousand");
		testSet.put(BigInteger.valueOf(1500), "one thousand, five hundred");
		testSet.put(BigInteger.valueOf(9999), "nine thousand, nine hundred ninety-nine");
		testSet.put(BigInteger.valueOf(50766), "fifty thousand, seven hundred sixty-six");
		testSet.put(BigInteger.valueOf(987687654), "nine hundred eighty-seven million, six hundred eighty-seven thousand, six hundred fifty-four");
		testSet.put(BigInteger.valueOf(9223372036854775807L), "nine quintillion, two hundred twenty-three quadrillion, three hundred seventy-two trillion, thirty-six billion, eight hundred fifty-four million, seven hundred seventy-five thousand, eight hundred seven");
		testSet.put(BigInteger.valueOf(1000).pow(20).multiply(BigInteger.valueOf(999)), "nine hundred ninety-nine vigintillion");
		for (BigInteger key : testSet.keySet()) {
			try {
				assertEquals(testSet.get(key), bigIntHelper.convertBigIntToEnglish(key));
			} catch (Exception e) {
				fail("Test failed for key " + key + " " + e.getLocalizedMessage());
			}
		}

		try {
			bigIntHelper.convertBigIntToEnglish(BigInteger.valueOf(1000).pow(21));
			fail("Test of exceeding max failed.");
		}catch (Exception e){
			assertTrue(true);
		}
		
	}

}
