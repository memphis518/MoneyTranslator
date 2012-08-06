package org.codingexercise;

import java.math.BigDecimal;
import java.math.BigInteger;

/** Generic Money class for handling numbers that represent money  
*
* @author Stephen Watters
* @author Stephen Watters
* @version 1.0
*/
public class Money {

	private BigDecimal amount;
	
	/**
	 * Empty constructor to create an empty Money object
	 */
	public Money(){}
	
	/**
	 * Constructor to create a Money object with the amount passed in
	 * as a BigDecimal value
	 *
	 * @param  amount value to instantiate the object with
	 */
	public Money(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Retrieve the amount stored in the object
	 * 
	 * @return Amount stored in the object
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Store or update the amount value in the object
	 * 
	 * @oaram amount value to store in the object
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * Convert the object's amount value to it's English representation.
	 * Note that it will round up the decimal to the hundredths place when 
	 * translating value to English.  There is also a upper limit of 999 
	 * vigintillion on the amount to be translated
	 * 
	 * @exception if the amount stored in the object is above 999 vigintillion or amount is not set
	 * @returns   the English representation of the amount stored in the object
	 */
	public String convertToEnglish() throws Exception {
		
		if(amount == null){
			throw new Exception("Amount is not set");
		}
		
		BigInteger dollars = amount.toBigInteger();
		BigDecimal cents = amount.remainder(BigDecimal.ONE).setScale(2,BigDecimal.ROUND_HALF_UP );
		String centsStr = cents.toString();
		BigIntHelper bigIntHelper = new BigIntHelper();
		String result = bigIntHelper.convertBigIntToEnglish(dollars) + " and " + centsStr.substring(2, 4) + "/100 dollars";
		return result;	
	}
	
	
}
