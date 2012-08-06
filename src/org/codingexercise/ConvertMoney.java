package org.codingexercise;

import java.math.BigDecimal;

public class ConvertMoney {

	public static void main(String[] args) {
		BigDecimal amount = new BigDecimal("2523.04");
		Money money = new Money(amount);
		String moneyString = null;
		try {
			moneyString = money.convertToEnglish();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(moneyString);
	}

}
