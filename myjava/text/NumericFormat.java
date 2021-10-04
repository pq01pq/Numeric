package myjava.text;

public class NumericFormat {
	
	public static String format(Number number) {
		String string = number.toString();
		switch(number.getClass().getSimpleName()) {
		case "Byte":
			string += "B";
			break;
		case "Short":
			string += "S";
			break;
		case "Long":
			string += "L";
			break;
		case "Float":
			string += "F";
			break;
		}
		return string;
	}
	
	public static Number parse(String string) throws NumberFormatException {
		if(isEmptyString(string)) {
			throw new NumberFormatException("Cannot parse empty string");
		}
		
		// determine sign and make string unsigned
		int sign = 1;
		char prefix = string.charAt(0);
		if(prefix == '-' || prefix == '+') {
			string = string.substring(1, string.length());
			if(string.length() == 0) {
				throw new NumberFormatException("Empty string after sign : " + string);
			}
			sign = ',' - prefix;	// '+' == 43 / ',' == 44 / '-' == 45
			prefix = string.charAt(0);
		}
		
		Number number;
		if(prefix == '0') {
			if(string.length() == 1) {
				return 0;
			}
			number = binaryNumberSystem(string.substring(1, string.length()));
		} else if(string.contains("e") || string.contains("E")) {
			number = exponentialNumberSystem(string);
		} else {
			number = decimalNumberSystem(string);
		}
		
		switch(number.getClass().getSimpleName()) {
		case "Byte":	return (byte) (sign * number.intValue());
		case "Short":	return (short) (sign * number.intValue());
		case "Integer":	return sign * number.intValue();
		case "Long":	return sign * number.longValue();
		case "Float":	return sign * number.floatValue();
		case "Double":	return sign * number.doubleValue();
		default :		return null;
		}
	}
	
	// 1. parse binary system number format
	private static Number binaryNumberSystem(String remainingPart) throws NumberFormatException {
		if(isEmptyString(remainingPart)) {
			throw new NumberFormatException("Cannot parse empty string");
		}
		char prefix = Character.toLowerCase(remainingPart.charAt(0));
		char suffix = Character.toUpperCase(remainingPart.charAt(remainingPart.length() - 1));
		int beginIndex = 0;
		int endIndex = remainingPart.length();
		int base;
		switch(prefix) {
		case 'b':	base = 2;	beginIndex++;	break;
		case 'x':	base = 16;	beginIndex++;	break;
		default :	base = 8;
		}
		if(isSuffix(suffix) && !isDigit(suffix)) {
			endIndex--;
		}
		if(endIndex <= beginIndex) {
			throw new NumberFormatException("Empty at number part : " + remainingPart);
		}
		String numberPart = remainingPart.substring(beginIndex, endIndex);
		Number number = buildNumber(numberPart, base);
		if(isSuffix(suffix) && !isDigit(suffix)) {
			return classifyBySuffix(number, suffix);
		} else if(number.getClass() == Double.class) {
			return number.doubleValue();
		} else {
			return number.intValue();
		}
	}
	
	// 2. parse exponential number format
	private static Number exponentialNumberSystem(String string) {
		if(isEmptyString(string)) {
			throw new NumberFormatException("Empty string");
		}
		char suffix = Character.toUpperCase(string.charAt(string.length() - 1));
		String numberPart = string;
		if(isSuffix(suffix)) {
			numberPart = numberPart.substring(0, numberPart.length() - 1);
		}
		String[] fullPartArray = fractionize(numberPart, "e");
		String basePart = fullPartArray[0];
		if(basePart.length() > 0 && basePart.charAt(0) == '0') {
			throw new NumberFormatException(
					"Expoenetial number must contain decimal format : " + string);
		}
		String exponentPart = null;
		if(fullPartArray.length > 1) {
			exponentPart = fullPartArray[1];
		}
		if(isEmptyString(exponentPart)) {
			throw new NumberFormatException("Exponential number must contain exponent");
		}
		
		int expSign = 1;
		char expPrefix = exponentPart.charAt(0);
		if(expPrefix == '+' || expPrefix == '-') {
			exponentPart = exponentPart.substring(1, exponentPart.length());
			if(exponentPart.length() == 0) {
				throw new NumberFormatException("Empty at number part : " + string);
			}
			expSign = ',' - expPrefix;	// '+' == 43 / ',' == 44 / '-' == 45
			expPrefix = exponentPart.charAt(0);
		}
		if(expPrefix == '0') {
			throw new NumberFormatException(
				"Expoenetial number must contain decimal format : " + string);
		}
		
		// thus both parts are independent number systems, parse each part recursively
		Number base, exponent;
		try {
			exponent = decimalNumberSystem(exponentPart);
		} catch(NumberFormatException e) {
			String message = "Illegal exponential format : " + string + "\n" + e.getMessage();
			throw new NumberFormatException(message);
		}
		
		if(exponent.getClass() == Double.class || exponent.getClass() == Float.class) {
			throw new NumberFormatException("Exponent must be integer : " + string);
		}
		
		try {
			base = decimalNumberSystem(basePart);
		} catch(NumberFormatException e) {
			String message = "Illegal exponential format : " + string + "\n" + e.getMessage();
			throw new NumberFormatException(message);
		}
		
		Number number = base.doubleValue() * Math.pow(10, expSign * exponent.doubleValue());
		if(isSuffix(suffix)) {
			return classifyBySuffix(number, suffix);
		} else if(base.getClass() == Double.class) {
			return number.doubleValue();
		} else {
			return number.intValue();
		}
	}
	
	// 3. parse decimal number format
	private static Number decimalNumberSystem(String string) throws NumberFormatException {
		if(isEmptyString(string)) {
			throw new NumberFormatException("Cannot parse empty string");
		}
		char suffix = Character.toUpperCase(string.charAt(string.length() - 1));
		String numberPart;
		if(isSuffix(suffix)) {
			numberPart = string.substring(0, string.length() - 1);
		} else {
			numberPart = string;
		}
		Number number = buildNumber(numberPart, 10);
		if(isSuffix(suffix)) {
			return classifyBySuffix(number, suffix);
		} else if(number.getClass() == Double.class) {
			return number.doubleValue();
		} else {
			return number.intValue();
		}
	}
	
	// actual number building factory
	private static Number buildNumber(String numberPart, long base) throws NumberFormatException {
		if(isEmptyString(numberPart)) {
			throw new NumberFormatException("Cannot parse empty string");
		}
		String[] fullPartArray = fractionize(numberPart, ".");
		String integerPart = fullPartArray[0];
		String fractionPart = null;
		if(fullPartArray.length > 1) {
			fractionPart = fullPartArray[1];
		}
		if(!hasLegalEdges(integerPart) || !hasLegalEdges(fractionPart)) {
			throw new NumberFormatException(
					"Edge of number format must be a digit : " + numberPart);
		}
		
		integerPart = removeTokens(integerPart);
		fractionPart = removeTokens(fractionPart);
		
		if(fractionPart != null && integerPart.length() == 0 && fractionPart.length() == 0) {
			throw new NumberFormatException(
					"Both integer and fraction parts cannot be empty at the same time");
		}
		
		long digitValue;
		Number number = 0L;
		long multiplier = 1;
		for(int i = integerPart.length() - 1 ; i >= 0; i--) {
			digitValue = getDigitValue(integerPart.charAt(i));
			if(digitValue >= base || digitValue < 0) {
				throw new NumberFormatException(
						"Illegal digit in number system : " + numberPart +
						" (base=" + base + ")");
			}
			number = number.longValue() + digitValue * multiplier;
			multiplier *= base;
		}
		
		if(fractionPart != null) {
			// 소숫점이 있으면 double로 형변환
			number = number.doubleValue();
			long divisor = base;
			for(int i = 0; i < fractionPart.length(); i++) {
				digitValue = getDigitValue(fractionPart.charAt(i));
				if(digitValue >= base || digitValue < 0) {
					throw new NumberFormatException(
							"Illegal digit in number system : " + numberPart +
							" (base=" + base + ")");
				}
				number = number.doubleValue() + digitValue / (double) divisor;
				divisor *= base;
			}
		}
		
		return number;
	}
	
	private static Number classifyBySuffix(Number number, char suffix) throws NumberFormatException {
		suffix = Character.toUpperCase(suffix);
		switch (suffix) {
		case 'B':	return number.byteValue();
		case 'S':	return number.shortValue();
		case 'L':	return number.longValue();
		case 'F':	return number.floatValue();
		case '%':	return number.doubleValue() / 100.0;
		default:	throw new NumberFormatException("Illegal suffix : " + number + suffix);
		}
	}
	
	
	// helper methods
	
	private static String[] fractionize(String numberPart, String regex) throws NumberFormatException {
		if(numberPart == null) {
			return new String[1];
		}
		String lowerString = numberPart.toLowerCase();
		String lowerRegex = regex.toLowerCase();
		String[] fullPartArray;
		int regexIndex = lowerString.indexOf(lowerRegex);
		if(regexIndex == -1) {
			fullPartArray = new String[1];
			fullPartArray[0] = numberPart;
		} else {
			if(regexIndex != lowerString.lastIndexOf(lowerRegex)) {
				// contains regex more than one
				throw new NumberFormatException(
						"Contains more than one regex : " + numberPart +
						" (regex=" + regex + ")");
			}
			fullPartArray = new String[2];
			fullPartArray[0] = numberPart.substring(0, regexIndex);
			fullPartArray[1] = numberPart.substring(regexIndex + lowerRegex.length(), numberPart.length());
		}
		return fullPartArray;
	}
	
	private static boolean isSuffix(char suffix) {
		suffix = Character.toUpperCase(suffix);
		switch(suffix) {
		case 'B': case 'S': case 'L': case 'F': case '%': return true;
		default : return false;
		}
	}
	
	// legal ex) 1,111,111 or empty string or null
	// illegal ex) ,111,111  111,111,  ,111,111,
	private static boolean hasLegalEdges(String numberFraction) {
		if(!isEmptyString(numberFraction)) {
			return isDigit(numberFraction.charAt(0))
					&& isDigit(numberFraction.charAt(numberFraction.length() - 1));
		}
		return true;
	}
	
	private static int getDigitValue(char digit) {
		digit = Character.toUpperCase(digit);
		if(digit >= '0' && digit <= '9') {
			return digit - '0';
		} else if(digit >= 'A' && digit <= 'F') {
			return 10 + digit - 'A';
		} else {
			return -1;
		}
	}
	
	private static boolean isDigit(char character) {
		character = Character.toUpperCase(character);
		return (character >= '0' && character <= '9') || (character >= 'A' && character <= 'F');
	}
	
	private static boolean isEmptyString(String string) {
		return string == null || string.length() == 0;
	}
	
	private static String removeTokens(String numberFraction) {
		if(numberFraction == null) {
			return null;
		}
		return numberFraction.replace(",", "").replace("_", "");
	}
	
}
