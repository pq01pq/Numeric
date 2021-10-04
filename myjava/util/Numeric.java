package myjava.util;

import myjava.text.NumericFormat;

/**
 * 숫자형식의 문자열과 숫자를
 * string : number 의 key : value 쌍으로 저장함
 * 현재 클래스에 맞지 않는 형식의 문자열이면
 * string만 저장되고 number은 null value가 됨
 */
public class Numeric implements Comparable<Numeric> {
	
	// 1. 기존 Number 클래스의 wrapper 클래스 기능에
	// parse(String) 기능을 추가하여 범용성을 늘림
	
	// fields
	
	private String string;
	private Number number;
	
	// constructors
	
	// construct by number
	public Numeric(Number number) {
		this.number = number;
		string = NumericFormat.format(number);
	}
	
	// construct by string
	public Numeric(String string) {
		try {
			number = NumericFormat.parse(string);
		} catch(NumberFormatException e) {
			number = null;
			System.out.println(e.getStackTrace()[0]);
			System.out.println(e.getMessage());
		}
		this.string = string;
	}
	
	// instance methods
	
	public Number getNumber() {
		return number;
	}
	
	public String getString() {
		return string;
	}
	
	public Class<? extends Number> getType() {
		return number == null ? null : number.getClass();
	}
	
	// sorting criteria
	@Override
	public int compareTo(Numeric other) {
		if(other == null) {
			return 1;
		}
		
		if(!this.isRealNumber()) {
			if(!other.isRealNumber()) {
				return 0;
			} else {
				return -1;
			}
		}
		
		if(this.isInteger() && other.isInteger()) {
			return Long.compare(this.getNumber().longValue(), other.getNumber().longValue());
		} else {
			return Double.compare(this.number.doubleValue(), other.number.doubleValue());
		}
	}
	
	// like JSON's {key:value} format
	@Override
	public String toString() {
		return "{Class:" + (number == null ? "null" : number.getClass().getSimpleName())
				+ ","
				+ "String:" + string
				+ ","
				+ "Number:" + (number == null ? "null" : number.toString()) + "}";
	}
	
	// 2. 숫자형식 판별
	
	// instance methods
	
	// 자연수
	public boolean isNaturalNumber() {
		if(this.isInteger() && this.number.longValue() >= 1L) {
			return true;
		}
		return false;
	}
	
	// 정수
	public boolean isInteger() {
		if(!this.isRealNumber()
				|| this.number.getClass() == Double.class
				|| this.number.getClass() == Float.class) {
			return false;
		}
		return true;
	}
	
	// 실수
	public boolean isRealNumber() {
		if(this.number == null) {
			return false;
		}
		return true;
	}
	
	// 3. 연산
	
	// single method
	public Numeric absolute() {
		Number number = this.number;
		if(number.doubleValue() < 0.0) {
			switch(number.getClass().getSimpleName()) {
			case "Byte":	number = (byte) (- number.intValue());	break;
			case "Short":	number = (short) (- number.intValue());	break;
			case "Integer":	number = - number.intValue();			break;
			case "Long":	number = - number.longValue();			break;
			case "Float":	number = - number.floatValue();			break;
			case "Double":	number = - number.doubleValue();		break;
			}
		}
		return new Numeric(number);
	}
	public Numeric root() {
		double result = Math.sqrt(this.number.doubleValue());
		if(this.isInteger() && result == (int) result) {
			if(this.number.getClass() == Long.class) {
				return new Numeric((long) result);
			} else {
				return new Numeric((int) result);
			}
		} else {
			return new Numeric(result);
		}
	}
	
	public Numeric root(double radical) {
		if(radical == 2.0) {
			return root();
		} else {
			return power(1.0 / radical);
		}
	}
	
	public Numeric square() {
		return power(2);
	}
	public Numeric cube() {
		return power(3);
	}
	public Numeric power(double exponent) {
		double result = Math.pow(this.number.doubleValue(), exponent);
		if(this.isInteger() && result == (int) result) {
			if(this.number.getClass() == Long.class) {
				return new Numeric((long) result);
			} else {
				return new Numeric((int) result);
			}
		} else {
			return new Numeric(result);
		}
	}
	
	// append method
	public Numeric add(Numeric other) {
		Number result;
		if(this.isInteger() && other.isInteger()) {
			if(this.number.getClass() == Long.class
					|| other.getNumber().getClass() == Long.class) {
				result = this.number.longValue() + other.getNumber().longValue();
			} else {
				result = this.number.intValue() + other.getNumber().intValue();
			}
		} else {
			result = this.number.doubleValue() + other.getNumber().doubleValue();
		}
		return new Numeric(result);
	}
	
	public Numeric subtract(Numeric other) {
		Number result;
		if(this.isInteger() && other.isInteger()) {
			if(this.number.getClass() == Long.class
					|| other.getNumber().getClass() == Long.class) {
				result = this.number.longValue() - other.getNumber().longValue();
			} else {
				result = this.number.intValue() - other.getNumber().intValue();
			}
		} else {
			result = this.number.doubleValue() - other.getNumber().doubleValue();
		}
		return new Numeric(result);
	}
	
	public Numeric multiply(Numeric other) {
		Number result;
		if(this.isInteger() && other.isInteger()) {
			if(this.number.getClass() == Long.class
					|| other.getNumber().getClass() == Long.class) {
				result = this.number.longValue() * other.getNumber().longValue();
			} else {
				result = this.number.intValue() * other.getNumber().intValue();
			}
		} else {
			result = this.number.doubleValue() * other.getNumber().doubleValue();
		}
		return new Numeric(result);
	}
	
	public Numeric divide(Numeric other) throws ArithmeticException {
		Number result;
		if(this.isInteger() && other.isInteger()
				&& this.number.longValue() % other.getNumber().longValue() == 0L) {
			if(this.number.getClass() == Long.class
					|| other.getNumber().getClass() == Long.class) {
				result = this.number.longValue() / other.getNumber().longValue();
			} else {
				result = this.number.intValue() / other.getNumber().intValue();
			}
		} else {
			result = this.number.doubleValue() / other.getNumber().doubleValue();
		}
		return new Numeric(result);
	}
	
	public Numeric modulo(Numeric other) {
		Number result;
		if(this.isInteger() && other.isInteger()) {
			if(this.number.getClass() == Long.class
					|| other.getNumber().getClass() == Long.class) {
				result = this.number.longValue() % other.getNumber().longValue();
			} else {
				result = this.number.intValue() % other.getNumber().intValue();
			}
		} else {
			result = this.number.doubleValue() % other.getNumber().doubleValue();
		}
		return new Numeric(result);
	}
}
