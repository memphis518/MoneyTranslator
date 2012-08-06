package org.codingexercise;

import java.math.BigInteger;

/** Helper class for Long numbers.   
*
* @author Stephen Watters
* @author Stephen Watters
* @version 1.0
*/
public class BigIntHelper {

	private String[] zeroToNineteen = { "zero",  "one",   "two",  "three", "four",   "five",   "six",
			"seven", "eight", "nine", "ten",   "eleven", "twelve", "thirteen",
			"fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	private String[] tens  = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	private String[] places = { "",
			"thousand",     "million",         "billion",       "trillion",       "quadrillion",
			"quintillion",  "sextillion",      "septillion",    "octillion",      "nonillion",
			"decillion",    "undecillion",     "duodecillion",  "tredecillion",   "quattuordecillion",
			"sexdecillion", "septendecillion", "octodecillion", "novemdecillion", "vigintillion" };

	/**
	 * Converts any BigInteger value up to and including 999 vigintillion to the English
	 * representation of the number.  
	 *
	 * @param  value  The value to be converted
	 * @exception     The method will throw an exception if the number supplied is too large to be translated
	 * @return        The English representation of the value passed in
	 */
	public String convertBigIntToEnglish(BigInteger value) throws Exception {	
		StringBuffer convertedLongToEnglish = new StringBuffer();
		BigInteger maxValue = BigInteger.valueOf(1000).pow(21);
		
		if (value.compareTo(maxValue) == 1 || value.compareTo(maxValue) == 0) {
			throw new Exception("Supplied too large of a number");
		}else if (value.compareTo( BigInteger.valueOf(100) ) == -1) {
			convertedLongToEnglish.append( convertUnder100(value) );
		}else if (value.compareTo(BigInteger.valueOf(1000)) == -1) {
			convertedLongToEnglish.append( convertUnder1000(value) );
		}else{
			// Loop through each "place" in the number and translates that section of the number
			for (int i = 0; i < places.length + 1; i++) {
				int exponent = i - 1;
				BigInteger placeValue = BigInteger.valueOf(1000).pow(i);
				if (placeValue.compareTo(value) == 1) {
					BigInteger mod = BigInteger.valueOf(1000).pow(exponent);
					BigInteger left = value.divide(mod);
					BigInteger right = value.subtract((left.multiply(mod)));
					convertedLongToEnglish.append( convertUnder1000(left) );
					convertedLongToEnglish.append( " " ); 
					convertedLongToEnglish.append( places[exponent] );
					if (right.compareTo(new BigInteger("0")) == 1) {
						convertedLongToEnglish.append( ", ");
						convertedLongToEnglish.append( convertBigIntToEnglish(right) );
					}
					break;
				}
			}
		}

		return convertedLongToEnglish.toString();

	}

	
	/**
	 * Converts any BigInteger value less than 100 to the English representation of the number
	 *
	 * @param  value  The value to be converted
	 * @exception     The method will throw an exception if the number supplied is greater than or equal to 100
	 * @return        The English representation of the value passed in
	 */
	private String convertUnder100(BigInteger value) throws Exception {
		StringBuffer convertedValue = new StringBuffer();

		if(value.compareTo( BigInteger.valueOf(100) ) == 1 ||
		   value.compareTo( BigInteger.valueOf(100) ) == 0) {
			throw new Exception("Supplied a value greater than 100");
		}else if (value.compareTo( BigInteger.valueOf(20) ) == -1) {
			convertedValue.append( zeroToNineteen[value.intValue()] );
		}else{
			for (int i = 0; i < 8; i++) {
				long placeValue = 20 + 10 * i;
				if ( value.compareTo(BigInteger.valueOf(placeValue + 10)) == -1) {
					convertedValue.append( tens[i] );
					BigInteger modValue = value.mod( BigInteger.valueOf(10) );
					if (modValue.compareTo(BigInteger.valueOf(0)) != 0){
						convertedValue.append( "-" );
						convertedValue.append( zeroToNineteen[modValue.intValue()] );
					}
					break;
				}        
			}
		}

		return convertedValue.toString();
	}

	/**
	 * Converts any BigInteger value less than 1000 to the English representation of the number.
	 *
	 * @param  value  The value to be converted
	 * @exception     The method will throw an exception if the number supplied is greater than or equal to 1000
	 * @return        The English representation of the value passed in
	 */
	private String convertUnder1000(BigInteger value) throws Exception {
		StringBuffer convertedValue = new StringBuffer();

		if(value.compareTo( BigInteger.valueOf(1000) ) == 1 ||
		   value.compareTo( BigInteger.valueOf(1000) ) == 0) {
			throw new Exception("Supplied a value greater than 1000");
		}

		BigInteger hundreds = value.divide( BigInteger.valueOf(100) );
		BigInteger mod = value.mod( BigInteger.valueOf(100) );
		if (hundreds.compareTo( BigInteger.valueOf(0) ) == 1 ) {
			convertedValue.append( zeroToNineteen[hundreds.intValue()] );
			convertedValue.append(" hundred");
			if (mod.compareTo( BigInteger.valueOf(0) ) == 1) {
				convertedValue.append(" ");
			}
		}
		if (mod.compareTo( BigInteger.valueOf(0) ) == 1) {
			convertedValue.append(convertUnder100(mod));
		}

		return convertedValue.toString();
	}

}
