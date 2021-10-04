package myjava.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import myjava.text.NumericFormat;

public class Numerics {
	
	// 1. 숫자형식 판별
	// 자연수
	public static boolean isNaturalNumber(String string) {
		if(string == null || string.length() == 0) {
			return false;
		}
		try {
			Number number = NumericFormat.parse(string);
			return isInteger(number) && number.longValue() >= 1L;
		} catch(NumberFormatException e) {}
		return false;
	}
	public static boolean isNaturalNumber(Number number) {
		return isInteger(number) && number.longValue() >= 1L;
	}
	public static boolean isNaturalNumber(Object object) {
		if(object == null) {
			return false;
		}
		if(object.getClass() == String.class) {
			String string = (String) object;
			return isNaturalNumber(string);
		} else if(object instanceof Number) {
			Number number = (Number) object;
			return isNaturalNumber(number);
		} else if(object.getClass() == Numeric.class) {
			Numeric numeric = (Numeric) object;
			return numeric.isNaturalNumber();
		} else {
			return false;
		}
	}
	
	public static boolean areNaturalNumbers(Collection<?> objects) {
		if(objects == null || objects.size() == 0) {
			return false;
		}
		// Object[]로 인자 전달
		return areNaturalNumbers(objects.toArray());
	}
	public static boolean areNaturalNumbers(Object[] objectArray) {
		if(objectArray == null || objectArray.length == 0) {
			return false;
		}
		for(int i = 0; i < objectArray.length; i++) {
			if(!areNaturalNumbers(objectArray[i])) {
				return false;
			}
		}
		return true;
	}
	// byte[], short[], int[], long[], float[], double[]은 여기로 옴
	public static boolean areNaturalNumbers(Object object) {
		if(object == null) {
			return false;
		}
		if(object.getClass().isArray()) {
			Object[] objectArray;
			try {
				objectArray = (Object[]) object;
			} catch(ClassCastException e) {
				objectArray = Batch.unfoldPrimitiveTypeArray(object);
			}
			return areNaturalNumbers(objectArray);
		} else if(object instanceof Collection) {
			Collection<?> objects = (Collection<?>) object;
			return areNaturalNumbers(objects.toArray());
		} else {
			return isNaturalNumber(object);
		}
	}
	
	// 정수
	public static boolean isInteger(String string) {
		if(string == null || string.length() == 0) {
			return false;
		}
		try {
			Number number = NumericFormat.parse(string);
			if(!isRealNumber(number)
					|| number.getClass() == Double.class
					|| number.getClass() == Float.class) {
				return false;
			}
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	public static boolean isInteger(Number number) {
		if(!isRealNumber(number)
				|| number.getClass() == Double.class
				|| number.getClass() == Float.class) {
			return false;
		}
		return true;
	}
	public static boolean isInteger(Object object) {
		if(object == null) {
			return false;
		}
		if(object.getClass() == String.class) {
			String string = (String) object;
			return isInteger(string);
		} else if(object instanceof Number) {
			Number number = (Number) object;
			return isInteger(number);
		} else if(object.getClass() == Numeric.class) {
			Numeric numeric = (Numeric) object;
			return numeric.isInteger();
		} else {
			return false;
		}
	}
	
	public static boolean areIntegers(Collection<?> objects) {
		if(objects == null || objects.size() == 0) {
			return false;
		}
		// Object[]로 인자 전달
		return areIntegers(objects.toArray());
	}
	public static boolean areIntegers(Object[] objectArray) {
		if(objectArray == null || objectArray.length == 0) {
			return false;
		}
		for(int i = 0; i < objectArray.length; i++) {
			if(!areIntegers(objectArray[i])) {
				return false;
			}
		}
		return true;
	}
	// byte[], short[], int[], long[], float[], double[]은 여기로 옴
	public static boolean areIntegers(Object object) {
		if(object == null) {
			return false;
		}
		if(object.getClass().isArray()) {
			Object[] objectArray;
			try {
				objectArray = (Object[]) object;
			} catch(ClassCastException e) {
				objectArray = Batch.unfoldPrimitiveTypeArray(object);
			}
			return areIntegers(objectArray);
		} else if(object instanceof Collection) {
			Collection<?> objects = (Collection<?>) object;
			return areIntegers(objects.toArray());
		} else {
			return isInteger(object);
		}
	}
	
	
	// 실수
	public static boolean isRealNumber(String string) {
		if(string == null || string.length() == 0) {
			return false;
		}
		try {
			NumericFormat.parse(string);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	public static boolean isRealNumber(Number number) {
		return number != null;
	}
	public static boolean isRealNumber(Object object) {
		if(object == null) {
			return false;
		}
		if(object.getClass() == String.class) {
			String string = (String) object;
			return isRealNumber(string);
		} else if(object instanceof Number) {
			Number number = (Number) object;
			return isRealNumber(number);
		} else if(object.getClass() == Numeric.class) {
			Numeric numeric = (Numeric) object;
			return numeric.isRealNumber();
		} else {
			return false;
		}
	}
	
	public static boolean areRealNumbers(Collection<?> objects) {
		if(objects == null || objects.size() == 0) {
			return false;
		}
		return areRealNumbers(objects.toArray());
	}
	public static boolean areRealNumbers(Object[] objectArray) {
		if(objectArray == null || objectArray.length == 0) {
			return false;
		}
		for(int i = 0; i < objectArray.length; i++) {
			if(!areRealNumbers(objectArray[i])) {
				return false;
			}
		}
		return true;
	}
	// boolean[], char[], byte[], short[],
	// int[], long[], float[], double[]도 여기로 옴
	public static boolean areRealNumbers(Object object) {
		if(object == null) {
			return false;
		}
		if(object.getClass().isArray()) {
			Object[] objectArray;
			try {
				objectArray = (Object[]) object;
			} catch(ClassCastException e) {
				objectArray = Batch.unfoldPrimitiveTypeArray(object);
			}
			return areRealNumbers(objectArray);
		} else if(object instanceof Collection) {
			Collection<?> objects = (Collection<?>) object;
			return areRealNumbers(objects.toArray());
		} else {
			return isRealNumber(object);
		}
	}
	
	
	// 2. 단순 합
	
	// primitive type arrays
	public static Number sum(byte... byteArray) {
		if(byteArray == null) {
			return null;
		}
		long longSum = 0L;
		for(int i = 0; i < byteArray.length; i++) {
			longSum += byteArray[i];
		}
		if(longSum < (long)Integer.MAX_VALUE) {
			return (int)longSum;
		} else {
			return longSum;
		}
	}
	public static Number sum(short... shortArray) {
		if(shortArray == null) {
			return null;
		}
		long longSum = 0L;
		for(int i = 0; i < shortArray.length; i++) {
			longSum += shortArray[i];
		}
		if(longSum < (long)Integer.MAX_VALUE) {
			return (int)longSum;
		} else {
			return longSum;
		}
	}
	public static Number sum(int... intArray) {
		if(intArray == null) {
			return null;
		}
		long longSum = 0L;
		for(int i = 0; i < intArray.length; i++) {
			longSum += intArray[i];
		}
		if(longSum < (long)Integer.MAX_VALUE) {
			return (int)longSum;
		} else {
			return longSum;
		}
	}
	public static Number sum(long... longArray) {
		if(longArray == null) {
			return null;
		}
		long longSum = 0L;
		for(int i = 0; i < longArray.length; i++) {
			longSum += longArray[i];
		}
		if(longSum < (long)Integer.MAX_VALUE) {
			return (int)longSum;
		} else {
			return longSum;
		}
	}
	public static Number sum(float... floatArray) {
		if(floatArray == null) {
			return null;
		}
		double doubleSum = 0.0;
		for(int i = 0; i < floatArray.length; i++) {
			doubleSum += floatArray[i];
		}
		return doubleSum;
	}
	public static Number sum(double... doubleArray) {
		if(doubleArray == null) {
			return null;
		}
		double doubleSum = 0.0;
		for(int i = 0; i < doubleArray.length; i++) {
			doubleSum += doubleArray[i];
		}
		return doubleSum;
	}
	
	// wrapper class arrays
	public static Number sum(Byte[] byteObjectArray) {
		return sum(byteObjectArray, Byte.class);
	}
	public static Number sum(Short[] shortObjectArray) {
		return sum(shortObjectArray, Short.class);
	}
	public static Number sum(Integer[] integerArray) {
		return sum(integerArray, Integer.class);
	}
	public static Number sum(Long[] longObjectArray) {
		return sum(longObjectArray, Long.class);
	}
	public static Number sum(Float[] floatObjectArray) {
		return sum(floatObjectArray, Float.class);
	}
	public static Number sum(Double[] doubleObjectArray) {
		return sum(doubleObjectArray, Double.class);
	}
	
	private static<T extends Number> Number sum(T[] numberArray, Class<T> type) {
		if(!areRealNumbers(numberArray)) {
			return null;
		}
		switch(type.getSimpleName()) {
		case "Byte":
		case "Short":
		case "Integer":
		case "Long":
			long longSum = 0L;
			for(int i = 0; i < numberArray.length; i++) {
				longSum += numberArray[i].longValue();
			}
			if(longSum < (long) Integer.MAX_VALUE) {
				return (int) longSum;
			} else {
				return longSum;
			}
		case "Float":
		case "Double":
			double doubleSum = 0.0;
			for(int i = 0; i < numberArray.length; i++) {
				doubleSum += numberArray[i].doubleValue();
			}
			return doubleSum;
		}
		
		return null;
	}
	
	// wrapper class lists
	public static Number sum(List<? extends Number> numbers) {
		if(!areRealNumbers(numbers)) {
			return null;
		}
		if(areIntegers(numbers)) {
			long longSum = 0L;
			for(Number number : numbers) {
				longSum += number.longValue();
			}
			if(longSum < (long)Integer.MAX_VALUE) {
				return (int)longSum;
			} else {
				return longSum;
			}
		} else {
			double doubleSum = 0.0;
			for(Number number : numbers) {
				doubleSum += number.doubleValue();
			}
			return doubleSum;
		}
	}
	
	// 3. 일괄 Numeric.format
	// String array
	
	// primitive type array
	public static String[] formatArrayOf(byte... byteArray) {
		if(byteArray == null) {
			return null;
		}
		String[] stringArray = new String[byteArray.length];
		for(int i = 0; i < byteArray.length; i++) {
			stringArray[i] = NumericFormat.format(byteArray[i]);
		}
		return stringArray;
	}
	public static String[] formatArrayOf(short... shortArray) {
		if(shortArray == null) {
			return null;
		}
		String[] stringArray = new String[shortArray.length];
		for(int i = 0; i < shortArray.length; i++) {
			stringArray[i] = NumericFormat.format(shortArray[i]);
		}
		return stringArray;
	}
	public static String[] formatArrayOf(int... intArray) {
		if(intArray == null) {
			return null;
		}
		String[] stringArray = new String[intArray.length];
		for(int i = 0; i < intArray.length; i++) {
			stringArray[i] = NumericFormat.format(intArray[i]);
		}
		return stringArray;
	}
	public static String[] formatArrayOf(long... longArray) {
		if(longArray == null) {
			return null;
		}
		String[] stringArray = new String[longArray.length];
		for(int i = 0; i < longArray.length; i++) {
			stringArray[i] = NumericFormat.format(longArray[i]);
		}
		return stringArray;
	}
	public static String[] formatArrayOf(float... floatArray) {
		if(floatArray == null) {
			return null;
		}
		String[] stringArray = new String[floatArray.length];
		for(int i = 0; i < floatArray.length; i++) {
			stringArray[i] = NumericFormat.format(floatArray[i]);
		}
		return stringArray;
	}
	public static String[] formatArrayOf(double... doubleArray) {
		if(doubleArray == null) {
			return null;
		}
		String[] stringArray = new String[doubleArray.length];
		for(int i = 0; i < doubleArray.length; i++) {
			stringArray[i] = NumericFormat.format(doubleArray[i]);
		}
		return stringArray;
	}
	
	// wrapper class arrays
	public static String[] formatArrayOf(Number[] numberArray) {
		if(numberArray == null) {
			return null;
		}
		String[] stringArray = new String[numberArray.length];
		for(int i = 0; i < numberArray.length; i++) {
			stringArray[i] = NumericFormat.format(numberArray[i]);
		}
		return stringArray;
	}
	
	// wrapper class lists
	// args : List<Byte, Short, Integer, Long, Float, Double>
	public static String[] formatArrayOf(List<? extends Number> numbers) {
		if(numbers == null || numbers.size() == 0) {
			return null;
		}
		String[] stringArray = new String[numbers.size()];
		for(int i = 0; i < numbers.size(); i++) {
			stringArray[i] = NumericFormat.format(numbers.get(i));
		}
		return stringArray;
	}
	
	
	// String List
	
	// primitive type arrays
	
	public static List<String> formatListOf(byte... byteArray){
		if(byteArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < byteArray.length; i++) {
			strings.add(NumericFormat.format(byteArray[i]));
		}
		return strings;
	}
	public static List<String> formatListOf(short... shortArray){
		if(shortArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < shortArray.length; i++) {
			strings.add(NumericFormat.format(shortArray[i]));
		}
		return strings;
	}
	public static List<String> formatListOf(int... intArray){
		if(intArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < intArray.length; i++) {
			strings.add(NumericFormat.format(intArray[i]));
		}
		return strings;
	}
	public static List<String> formatListOf(long... longArray){
		if(longArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < longArray.length; i++) {
			strings.add(NumericFormat.format(longArray[i]));
		}
		return strings;
	}
	public static List<String> formatListOf(float... floatArray){
		if(floatArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < floatArray.length; i++) {
			strings.add(NumericFormat.format(floatArray[i]));
		}
		return strings;
	}
	public static List<String> formatListOf(double... doubleArray){
		if(doubleArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < doubleArray.length; i++) {
			strings.add(NumericFormat.format(doubleArray[i]));
		}
		return strings;
	}
	
	// wrapper class arrays
	public static List<String> formatListOf(Number[] numberArray){
		if(numberArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < numberArray.length; i++) {
			strings.add(NumericFormat.format(numberArray[i]));
		}
		return strings;
	}
	
	// wrapper class lists
	// args : List<Byte, Short, Integer, Long, Float, Double>
	public static List<String> formatListOf(List<? extends Number> numbers){
		if(numbers == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(Number number : numbers) {
			strings.add(NumericFormat.format(number));
		}
		return strings;
	}
	
	// 4. 일괄 Numeric.parse
	
	public static Number[] parseNumberArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		Number[] array = new Number[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			try {
				array[i] = NumericFormat.parse(stringArray[i]);
			} catch (NumberFormatException e) {
				array[i] = null;
			}
		}
		return array;
	}
	public static Number[] parseNumberArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		Number[] array = new Number[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			try {
				array[i] = NumericFormat.parse(strings.get(i));
			} catch (NumberFormatException e) {
				array[i] = null;
			}
		}
		return array;
	}
	
	public static List<Number> parseNumberList(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		List<Number> list = new ArrayList<>();
		for(int i = 0; i < stringArray.length; i++) {
			try {
				list.add(NumericFormat.parse(stringArray[i]));
			} catch (NumberFormatException e) {
				list.add(null);
			}
		}
		return list;
	}
	public static List<Number> parseNumberList(List<String> strings) {
		if(strings == null) {
			return null;
		}
		List<Number> list = new ArrayList<>();
		for(int i = 0; i < strings.size(); i++) {
			try {
				list.add(NumericFormat.parse(strings.get(i)));
			} catch (NumberFormatException e) {
				list.add(null);
			}
		}
		return list;
	}
	
}
