package myjava.util;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Array;

/**
 * 숫자형식의 문자열이나 숫자로 이루어진 배열이나 리스트 간의 빠른 전환을 도와줌
 */
public class Batch {
	
	// 기본타입 배열은 Object[]로 캐스팅할 수 없기 때문에
	// 타입을 확인해서 wrapper class 배열로 펴서 Object[]로 넣어주는 작업	
	static Object[] unfoldPrimitiveTypeArray(Object object) {
		switch(object.getClass().getSimpleName()) {
		case "boolean[]":	return boxingAll((boolean[]) object);
		case "char[]":		return boxingAll((char[]) object);
		case "byte[]":		return boxingAll((byte[]) object);
		case "short[]":		return boxingAll((short[]) object);
		case "int[]":		return boxingAll((int[]) object);
		case "long[]":		return boxingAll((long[]) object);
		case "float[]":		return boxingAll((float[]) object);
		case "double[]":	return boxingAll((double[]) object);
		default :			return null;
		}
	}
	
	// 1. 리스트의 생성과 해제
	
	// batch listing
	public static<T> List<T> listing(T[] objectArray) {
		if(objectArray == null) {
			return null;
		}
		List<T> list = new ArrayList<>();
		for(int i = 0; i < objectArray.length; i++) {
			list.add(objectArray[i]);
		}
		return list;
	}
	
	public static List<Boolean> listingBooleanArray(boolean... booleanArray) {
		if(booleanArray == null) {
			return null;
		}
		List<Boolean> list = new ArrayList<>();
		for(int i = 0; i < booleanArray.length; i++) {
			list.add(booleanArray[i]);
		}
		return list;
	}
	public static List<Character> listingCharArray(char... charArray) {
		if(charArray == null) {
			return null;
		}
		List<Character> list = new ArrayList<>();
		for(int i = 0; i < charArray.length; i++) {
			list.add(charArray[i]);
		}
		return list;
	}
	public static List<Byte> listingByteArray(byte... byteArray) {
		if(byteArray == null) {
			return null;
		}
		List<Byte> list = new ArrayList<>();
		for(int i = 0; i < byteArray.length; i++) {
			list.add(byteArray[i]);
		}
		return list;
	}
	public static List<Short> listingShortArray(short... shortArray) {
		if(shortArray == null) {
			return null;
		}
		List<Short> list = new ArrayList<>();
		for(int i = 0; i < shortArray.length; i++) {
			list.add(shortArray[i]);
		}
		return list;
	}
	public static List<Integer> listingIntArray(int... intArray) {
		if(intArray == null) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < intArray.length; i++) {
			list.add(intArray[i]);
		}
		return list;
	}
	public static List<Long> listingLongArray(long... longArray) {
		if(longArray == null) {
			return null;
		}
		List<Long> list = new ArrayList<>();
		for(int i = 0; i < longArray.length; i++) {
			list.add(longArray[i]);
		}
		return list;
	}
	public static List<Float> listingFloatArray(float... floatArray) {
		if(floatArray == null) {
			return null;
		}
		List<Float> list = new ArrayList<>();
		for(int i = 0; i < floatArray.length; i++) {
			list.add(floatArray[i]);
		}
		return list;
	}
	public static List<Double> listingDoubleArray(double... doubleArray) {
		if(doubleArray == null) {
			return null;
		}
		List<Double> list = new ArrayList<>();
		for(int i = 0; i < doubleArray.length; i++) {
			list.add(doubleArray[i]);
		}
		return list;
	}
	
	
	// batch unlisting
	@SuppressWarnings("unchecked")
	public static<T> T[] unListing(List<T> objects, Class<T> type) {
		if(objects == null) {
			return null;
		}
		T[] objectArray = (T[]) Array.newInstance(type, objects.size());
		return objects.toArray(objectArray);
	}
	
	public static boolean[] unListingBooleans(List<Boolean> booleans) {
		if(booleans == null) {
			return null;
		}
		boolean[] array = new boolean[booleans.size()];
		for(int i = 0; i < booleans.size(); i++) {
			array[i] = booleans.get(i);
		}
		return array;
	}
	public static char[] unListingCharacters(List<Character> characters) {
		if(characters == null) {
			return null;
		}
		char[] array = new char[characters.size()];
		for(int i = 0; i < characters.size(); i++) {
			array[i] = characters.get(i);
		}
		return array;
	}
	public static byte[] unListingBytes(List<Byte> bytes) {
		if(bytes == null) {
			return null;
		}
		byte[] array = new byte[bytes.size()];
		for(int i = 0; i < bytes.size(); i++) {
			array[i] = bytes.get(i);
		}
		return array;
	}
	public static short[] unListingShorts(List<Short> shorts) {
		if(shorts == null) {
			return null;
		}
		short[] array = new short[shorts.size()];
		for(int i = 0; i < shorts.size(); i++) {
			array[i] = shorts.get(i);
		}
		return array;
	}
	public static int[] unListingIntegers(List<Integer> integers) {
		if(integers == null) {
			return null;
		}
		int[] array = new int[integers.size()];
		for(int i = 0; i < integers.size(); i++) {
			array[i] = integers.get(i);
		}
		return array;
	}
	public static float[] unListingFloats(List<Float> floats) {
		if(floats == null) {
			return null;
		}
		float[] array = new float[floats.size()];
		for(int i = 0; i < floats.size(); i++) {
			array[i] = floats.get(i);
		}
		return array;
	}
	public static double[] unListingDoubles(List<Double> doubles) {
		if(doubles == null) {
			return null;
		}
		double[] array = new double[doubles.size()];
		for(int i = 0; i < doubles.size(); i++) {
			array[i] = doubles.get(i);
		}
		return array;
	}
	
	
	// 2. wrapper class의 일괄 boxing과 unboxing
	
	// batch boxing
	public static Boolean[] boxingAll(boolean... booleanArray) {
		if(booleanArray == null) {
			return null;
		}
		Boolean[] objectArray = new Boolean[booleanArray.length];
		for(int i = 0; i < booleanArray.length; i++) {
			objectArray[i] = booleanArray[i];
		}
		return objectArray;
	}
	public static Character[] boxingAll(char... charArray) {
		if(charArray == null) {
			return null;
		}
		Character[] objectArray = new Character[charArray.length];
		for(int i = 0; i < charArray.length; i++) {
			objectArray[i] = charArray[i];
		}
		return objectArray;
	}
	public static Byte[] boxingAll(byte... byteArray) {
		if(byteArray == null) {
			return null;
		}
		Byte[] objectArray = new Byte[byteArray.length];
		for(int i = 0; i < byteArray.length; i++) {
			objectArray[i] = byteArray[i];
		}
		return objectArray;
	}
	public static Short[] boxingAll(short... shortArray) {
		if(shortArray == null) {
			return null;
		}
		Short[] objectArray = new Short[shortArray.length];
		for(int i = 0; i < shortArray.length; i++) {
			objectArray[i] = shortArray[i];
		}
		return objectArray;
	}
	public static Integer[] boxingAll(int... intArray) {
		if(intArray == null) {
			return null;
		}
		Integer[] objectArray = new Integer[intArray.length];
		for(int i = 0; i < intArray.length; i++) {
			objectArray[i] = intArray[i];
		}
		return objectArray;
	}
	public static Long[] boxingAll(long... longArray) {
		if(longArray == null) {
			return null;
		}
		Long[] objectArray = new Long[longArray.length];
		for(int i = 0; i < longArray.length; i++) {
			objectArray[i] = longArray[i];
		}
		return objectArray;
	}
	public static Float[] boxingAll(float... floatArray) {
		if(floatArray == null) {
			return null;
		}
		Float[] objectArray = new Float[floatArray.length];
		for(int i = 0; i < floatArray.length; i++) {
			objectArray[i] = floatArray[i];
		}
		return objectArray;
	}
	public static Double[] boxingAll(double... doubleArray) {
		if(doubleArray == null) {
			return null;
		}
		Double[] objectArray = new Double[doubleArray.length];
		for(int i = 0; i < doubleArray.length; i++) {
			objectArray[i] = doubleArray[i];
		}
		return objectArray;
	}
	
	// batch unboxing
	public static boolean[] unBoxingAll(Boolean[] booleanObjectArray) {
		boolean[] array = new boolean[booleanObjectArray.length];
		for(int i = 0; i < booleanObjectArray.length; i++) {
			array[i] = booleanObjectArray[i];
		}
		return array;
	}
	public static char[] unBoxingAll(Character[] characterObjectArray) {
		char[] array = new char[characterObjectArray.length];
		for(int i = 0; i < characterObjectArray.length; i++) {
			array[i] = characterObjectArray[i];
		}
		return array;
	}
	public static byte[] unBoxingAll(Byte[] byteObjectArray) {
		byte[] array = new byte[byteObjectArray.length];
		for(int i = 0; i < byteObjectArray.length; i++) {
			array[i] = byteObjectArray[i];
		}
		return array;
	}
	public static short[] unBoxingAll(Short[] shortObjectArray) {
		short[] array = new short[shortObjectArray.length];
		for(int i = 0; i < shortObjectArray.length; i++) {
			array[i] = shortObjectArray[i];
		}
		return array;
	}
	public static int[] unBoxingAll(Integer[] integerArray) {
		int[] array = new int[integerArray.length];
		for(int i = 0; i < integerArray.length; i++) {
			array[i] = integerArray[i];
		}
		return array;
	}
	public static long[] unBoxingAll(Long[] longObjectArray) {
		long[] array = new long[longObjectArray.length];
		for(int i = 0; i < longObjectArray.length; i++) {
			array[i] = longObjectArray[i];
		}
		return array;
	}
	public static float[] unBoxingAll(Float[] floatObjectArray) {
		float[] array = new float[floatObjectArray.length];
		for(int i = 0; i < floatObjectArray.length; i++) {
			array[i] = floatObjectArray[i];
		}
		return array;
	}
	public static double[] unBoxingAll(Double[] doubleObjectArray) {
		double[] array = new double[doubleObjectArray.length];
		for(int i = 0; i < doubleObjectArray.length; i++) {
			array[i] = doubleObjectArray[i];
		}
		return array;
	}
	
	
	// 3. 숫자 배열 또는 리스트에서 문자열 배열 또는 리스트로의 변환
	
	// String.valueOf(null Integer)은 "null" 문자열을 리턴하므로
	// .toString()으로 NullPointerException을 유도함
	
	// String array
	
	// primitive type array
	public static String[] stringArrayOf(byte... byteArray) {
		if(byteArray == null) {
			return null;
		}
		String[] stringArray = new String[byteArray.length];
		for(int i = 0; i < byteArray.length; i++) {
			stringArray[i] = String.valueOf(byteArray[i]);
		}
		return stringArray;
	}
	public static String[] stringArrayOf(short... shortArray) {
		if(shortArray == null) {
			return null;
		}
		String[] stringArray = new String[shortArray.length];
		for(int i = 0; i < shortArray.length; i++) {
			stringArray[i] = String.valueOf(shortArray[i]);
		}
		return stringArray;
	}
	public static String[] stringArrayOf(int... intArray) {
		if(intArray == null) {
			return null;
		}
		String[] stringArray = new String[intArray.length];
		for(int i = 0; i < intArray.length; i++) {
			stringArray[i] = String.valueOf(intArray[i]);
		}
		return stringArray;
	}
	public static String[] stringArrayOf(long... longArray) {
		if(longArray == null) {
			return null;
		}
		String[] stringArray = new String[longArray.length];
		for(int i = 0; i < longArray.length; i++) {
			stringArray[i] = String.valueOf(longArray[i]);
		}
		return stringArray;
	}
	public static String[] stringArrayOf(float... floatArray) {
		if(floatArray == null) {
			return null;
		}
		String[] stringArray = new String[floatArray.length];
		for(int i = 0; i < floatArray.length; i++) {
			stringArray[i] = String.valueOf(floatArray[i]);
		}
		return stringArray;
	}
	public static String[] stringArrayOf(double... doubleArray) {
		if(doubleArray == null) {
			return null;
		}
		String[] stringArray = new String[doubleArray.length];
		for(int i = 0; i < doubleArray.length; i++) {
			stringArray[i] = String.valueOf(doubleArray[i]);
		}
		return stringArray;
	}
	
	// wrapper class arrays
	public static String[] stringArrayOf(Number[] numberArray) {
		if(numberArray == null) {
			return null;
		}
		String[] stringArray = new String[numberArray.length];
		for(int i = 0; i < numberArray.length; i++) {
			stringArray[i] = numberArray[i].toString();
		}
		return stringArray;
	}
	
	// wrapper class lists
	// args : List<Byte, Short, Integer, Long, Float, Double>
	public static String[] stringArrayOf(List<? extends Number> numbers) {
		if(numbers == null || numbers.size() == 0) {
			return null;
		}
		String[] stringArray = new String[numbers.size()];
		for(int i = 0; i < numbers.size(); i++) {
			stringArray[i] = numbers.get(i).toString();
		}
		return stringArray;
	}
	
	
	// String List
	
	// primitive type arrays
	
	public static List<String> stringListOf(byte... byteArray){
		if(byteArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < byteArray.length; i++) {
			strings.add(String.valueOf(byteArray[i]));
		}
		return strings;
	}
	public static List<String> stringListOf(short... shortArray){
		if(shortArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < shortArray.length; i++) {
			strings.add(String.valueOf(shortArray[i]));
		}
		return strings;
	}
	public static List<String> stringListOf(int... intArray){
		if(intArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < intArray.length; i++) {
			strings.add(String.valueOf(intArray[i]));
		}
		return strings;
	}
	public static List<String> stringListOf(long... longArray){
		if(longArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < longArray.length; i++) {
			strings.add(String.valueOf(longArray[i]));
		}
		return strings;
	}
	public static List<String> stringListOf(float... floatArray){
		if(floatArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < floatArray.length; i++) {
			strings.add(String.valueOf(floatArray[i]));
		}
		return strings;
	}
	public static List<String> stringListOf(double... doubleArray){
		if(doubleArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < doubleArray.length; i++) {
			strings.add(String.valueOf(doubleArray[i]));
		}
		return strings;
	}
	
	// wrapper class arrays	
	public static List<String> stringListOf(Number[] numberArray){
		if(numberArray == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < numberArray.length; i++) {
			strings.add(numberArray[i].toString());
		}
		return strings;
	}
	
	// wrapper class lists
	// args : List<Byte, Short, Integer, Long, Float, Double>
	public static List<String> stringListOf(List<? extends Number> numbers){
		if(numbers == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for(Number number : numbers) {
			strings.add(number.toString());
		}
		return strings;
	}
	
	
	// 4. 숫자형식의 문자열 배열 또는 리스트에서 숫자 배열 또는 리스트로의 변환
	
	// parse()와 valueOf()모두
	// null을 포함한 숫자형식에 맞지 않는 String에 대하여
	// NumberFormatException을 발생시킴
	
	// byte array
	public static byte[] parseByteArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		byte[] array = new byte[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			array[i] = Byte.parseByte(stringArray[i]);
		}
		return array;
	}
	public static byte[] parseByteArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		byte[] array = new byte[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			array[i] = Byte.parseByte(strings.get(i));
		}
		return array;
	}
	// short array
	public static short[] parseShortArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		short[] array = new short[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			array[i] = Short.parseShort(stringArray[i]);
		}
		return array;
	}
	public static short[] parseShortArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		short[] array = new short[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			array[i] = Short.parseShort(strings.get(i));
		}
		return array;
	}
	// int array
	public static int[] parseIntArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		int[] array = new int[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			array[i] = Integer.parseInt(stringArray[i]);
		}
		return array;
	}
	public static int[] parseIntArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		int[] array = new int[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			array[i] = Integer.parseInt(strings.get(i));
		}
		return array;
	}
	// long array
	public static long[] parseLongArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		long[] array = new long[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			array[i] = Long.parseLong(stringArray[i]);
		}
		return array;
	}
	public static long[] parseLongArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		long[] array = new long[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			array[i] = Long.parseLong(strings.get(i));
		}
		return array;
	}
	// float array
	public static float[] parseFloatArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		float[] array = new float[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			array[i] = Float.parseFloat(stringArray[i]);
		}
		return array;
	}
	public static float[] parseFloatArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		float[] array = new float[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			array[i] = Float.parseFloat(strings.get(i));
		}
		return array;
	}
	// double array
	public static double[] parseDoubleArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		double[] array = new double[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			array[i] = Double.parseDouble(stringArray[i]);
		}
		return array;
	}
	public static double[] parseDoubleArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		double[] array = new double[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			array[i] = Double.parseDouble(strings.get(i));
		}
		return array;
	}
	
	
	// byte object array
	public static Byte[] parseByteObjectArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		Byte[] objectArray = new Byte[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			objectArray[i] = Byte.valueOf(stringArray[i]);
		}
		return objectArray;
	}
	public static Byte[] parseByteObjectArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		Byte[] objectArray = new Byte[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			objectArray[i] = Byte.valueOf(strings.get(i));
		}
		return objectArray;
	}
	// Short Object array
	public static Short[] parseShortObjectArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		Short[] objectArray = new Short[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			objectArray[i] = Short.valueOf(stringArray[i]);
		}
		return objectArray;
	}
	public static Short[] parseShortObjectArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		Short[] objectArray = new Short[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			objectArray[i] = Short.valueOf(strings.get(i));
		}
		return objectArray;
	}
	// Integer array
	public static Integer[] parseIntegerArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		Integer[] objectArray = new Integer[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			objectArray[i] = Integer.valueOf(stringArray[i]);
		}
		return objectArray;
	}
	public static Integer[] parseIntegerArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		Integer[] objectArray = new Integer[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			objectArray[i] = Integer.valueOf(strings.get(i));
		}
		return objectArray;
	}
	// Long Object array
	public static Long[] parseLongObjectArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		Long[] objectArray = new Long[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			objectArray[i] = Long.valueOf(stringArray[i]);
		}
		return objectArray;
	}
	public static Long[] parseLongObjectArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		Long[] objectArray = new Long[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			objectArray[i] = Long.valueOf(strings.get(i));
		}
		return objectArray;
	}
	// Float Object array
	public static Float[] parseFloatObjectArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		Float[] objectArray = new Float[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			objectArray[i] = Float.valueOf(stringArray[i]);
		}
		return objectArray;
	}
	public static Float[] parseFloatObjectArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		Float[] objectArray = new Float[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			objectArray[i] = Float.valueOf(strings.get(i));
		}
		return objectArray;
	}
	// Double Object array
	public static Double[] parseDoubleObjectArray(String... stringArray) {
		if(stringArray == null) {
			return null;
		}
		Double[] objectArray = new Double[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			objectArray[i] = Double.valueOf(stringArray[i]);
		}
		return objectArray;
	}
	public static Double[] parseDoubleObjectArray(List<String> strings) {
		if(strings == null) {
			return null;
		}
		Double[] objectArray = new Double[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			objectArray[i] = Double.valueOf(strings.get(i));
		}
		return objectArray;
	}
	
	
	// Byte List
	public static List<Byte> parseByteList(String... stringArray){
		if(stringArray == null) {
			return null;
		}
		List<Byte> list = new ArrayList<>();
		for(int i = 0; i < stringArray.length; i++) {
			list.add(Byte.valueOf(stringArray[i]));
		}
		return list;
	}
	public static List<Byte> parseByteList(List<String> strings){
		if(strings == null) {
			return null;
		}
		List<Byte> list = new ArrayList<>();
		for(String string : strings) {
			list.add(Byte.valueOf(string));
		}
		return list;
	}
	// Short List
	public static List<Short> parseShortList(String... stringArray){
		if(stringArray == null) {
			return null;
		}
		List<Short> list = new ArrayList<>();
		for(int i = 0; i < stringArray.length; i++) {
			list.add(Short.valueOf(stringArray[i]));
		}
		return list;
	}
	public static List<Short> parseShortList(List<String> strings){
		if(strings == null) {
			return null;
		}
		List<Short> list = new ArrayList<>();
		for(String string : strings) {
			list.add(Short.valueOf(string));
		}
		return list;
	}
	// Integer List
	public static List<Integer> parseIntegerList(String... stringArray){
		if(stringArray == null) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < stringArray.length; i++) {
			list.add(Integer.valueOf(stringArray[i]));
		}
		return list;
	}
	public static List<Integer> parseIntegerList(List<String> strings){
		if(strings == null) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		for(String string : strings) {
			list.add(Integer.valueOf(string));
		}
		return list;
	}
	// Long List
	public static List<Long> parseLongList(String... stringArray){
		if(stringArray == null) {
			return null;
		}
		List<Long> list = new ArrayList<>();
		for(int i = 0; i < stringArray.length; i++) {
			list.add(Long.valueOf(stringArray[i]));
		}
		return list;
	}
	public static List<Long> parseLongList(List<String> strings){
		if(strings == null) {
			return null;
		}
		List<Long> list = new ArrayList<>();
		for(String string : strings) {
			list.add(Long.valueOf(string));
		}
		return list;
	}
	// Float List
	public static List<Float> parseFloatList(String... stringArray){
		if(stringArray == null) {
			return null;
		}
		List<Float> list = new ArrayList<>();
		for(int i = 0; i < stringArray.length; i++) {
			list.add(Float.valueOf(stringArray[i]));
		}
		return list;
	}
	public static List<Float> parseFloatList(List<String> strings){
		if(strings == null) {
			return null;
		}
		List<Float> list = new ArrayList<>();
		for(String string : strings) {
			list.add(Float.valueOf(string));
		}
		return list;
	}
	// Double List
	public static List<Double> parseDoubleList(String... stringArray){
		if(stringArray == null) {
			return null;
		}
		List<Double> list = new ArrayList<>();
		for(int i = 0; i < stringArray.length; i++) {
			list.add(Double.valueOf(stringArray[i]));
		}
		return list;
	}
	public static List<Double> parseDoubleList(List<String> strings){
		if(strings == null) {
			return null;
		}
		List<Double> list = new ArrayList<>();
		for(String string : strings) {
			list.add(Double.valueOf(string));
		}
		return list;
	}
}
