package org.codingexercise.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.codingexercise.Money;
import org.junit.Test;

public class MoneyTests {

	@Test
	public void testConvertToEnglish() {
		
		HashMap<BigDecimal, String> testSet = new HashMap<BigDecimal, String>();
		testSet.put(BigDecimal.valueOf(0), "zero and 00/100 dollars");
		testSet.put(BigDecimal.valueOf(0.0), "zero and 00/100 dollars");
		testSet.put(BigDecimal.valueOf(8765.12), "eight thousand, seven hundred sixty-five and 12/100 dollars");
		testSet.put(BigDecimal.valueOf(76515.67), "seventy-six thousand, five hundred fifteen and 67/100 dollars");
		testSet.put(BigDecimal.valueOf(76617.678), "seventy-six thousand, six hundred seventeen and 68/100 dollars");
		testSet.put(BigDecimal.valueOf(766611.1), "seven hundred sixty-six thousand, six hundred eleven and 10/100 dollars");
		
		for (BigDecimal key : testSet.keySet()) {
			try {
				Money money = new Money(key);
				assertEquals(testSet.get(key), money.convertToEnglish());
			} catch (Exception e) {
				fail("Test failed for key " + key + " " + e.getLocalizedMessage());
			}
		}
		
		try {
			Money money = new Money();
			money.convertToEnglish();
			fail("Test amount not setting amount failed");
		}catch (Exception e){
			assertTrue(true);
		}
		
	}

}
