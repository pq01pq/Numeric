package myjava;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import myjava.util.Numeric;
import myjava.util.NumericList;
import myjava.util.Numerics;

/**
 * 
 * 본 패키지는 쿠키에서 꺼낸 문자열을 split한 뒤
 * 일일이 parseInt하다가 열받아서 제작에 돌입하였다.
 * 언젠간 유용하게 쓰일 날이 오면 좋을 것 같다.
 *
 */
public class TestMain {
	
	public static void main(String[] args) throws ParseException {
		NumericList numerics = new NumericList();
		
		// part 1. 10진수
		
		// non-suffix : 기본 Integer, Double
		numerics.add("1");
		numerics.add("+1");
		numerics.add("-1");
		
		numerics.add("1.1");
		numerics.add("+1.1");
		numerics.add("-1.1");
		
		numerics.add("1.");
		numerics.add("+1.");
		numerics.add("-1.");
		
		numerics.add(".1");
		numerics.add("+.1");
		numerics.add("-.1");
		
		// 10진수 suffix (B : Byte, S : Short, L : Long, F : Float)
		numerics.add("2b");
		numerics.add("+2s");
		numerics.add("-2l");
		
		numerics.add("2.2f");
		numerics.add("+2.2f");
		numerics.add("-2.2f");
		
		numerics.add("2.f");
		numerics.add("+2.f");
		numerics.add("-2.f");
		
		numerics.add(".2f");
		numerics.add("+.2f");
		numerics.add("-.2f");
		
		// 10진수 suffix 퍼센트
		numerics.add("25%");
		numerics.add("+25%");
		numerics.add("-25%");
		
		
		// part 2. 이진수 계열
		// 자바는 2, 8, 16 진수의 소숫점 형태를 지원하지 않지만 우겨넣었다
		
		// 2진수
		numerics.add("0b11");
		numerics.add("0b11.01");
		numerics.add("-0b11");
		numerics.add("-0b11.01");
		
		// 2진수 suffix (B, F는 비허용)
		numerics.add("0b11s");
		numerics.add("-0b11L");
		
		// 8진수
		numerics.add("054");
		numerics.add("054.02");
		numerics.add("-054");
		numerics.add("-054.02");
		
		// 8진수 suffix (B, F는 비허용)
		numerics.add("054s");
		numerics.add("-054L");
		
		// 16진수
		numerics.add("0xA3F7");
		numerics.add("0xA3F7.0B");
		numerics.add("-0xA3F7");
		numerics.add("-0xA3F7.0B");
		
		// 16진수 suffix (B, F는 비허용)
		numerics.add("0xF7s");
		numerics.add("-0xA3F7L");
		
		// 2, 8, 16진수 퍼센트
		numerics.add("0b11001%");
		numerics.add("+0b11001%");
		numerics.add("-0b11001%");
		numerics.add("031%");
		numerics.add("+031%");
		numerics.add("-031%");
		numerics.add("0x19%");
		numerics.add("+0x19%");
		numerics.add("-0x19%");
		
		
		// part 3. 지수
		// 지수는 e를 기준으로 양쪽이 서로 독립적인 10진수 시스템이다
		
		numerics.add("5e2");	// == 5 x 10^2
		numerics.add("+5e2");
		numerics.add("-5e2");
		numerics.add("5e+2");
		numerics.add("5e-2");
		
		numerics.add(".5e2");	// == 0.5 x 10^2
		numerics.add("5e.2");	// == 5 x 10^0.2
		numerics.add("5.e2");
		numerics.add("5e2.");
		
		numerics.add("5.23e2.12");
		numerics.add("+5.23e-2.12");
		numerics.add("-5.23e+2.12");
		
		// 등등 경우의 수가 너무 많아서 생략
		
		// 지수 suffix
		numerics.add("5e2L");
		numerics.add("5e2F");
		numerics.add("5.23e2.12F");
		
		for(Numeric numeric : numerics) {
			System.out.println(numeric + "\n");
		}
		
		System.out.println("\n====================================================\n");
		
		// 사용 예시 : ipv6주소 숫자값 변환
		
		String ipv6 = "fe80::1d1b:4fb4:168c:f659%17";
		ipv6 = ipv6.substring(0, ipv6.indexOf('%'));
		String[] tokenArray = ipv6.split(":");
		for(int i = 0; i < tokenArray.length; i++) {
			if(tokenArray[i].equals("")) {
				tokenArray[i] = "0";
			}
			tokenArray[i] = "0x" + tokenArray[i];
		}
		System.out.println(Arrays.toString(tokenArray));
		
		NumericList ipv6List = new NumericList(tokenArray);
		List<Number> ipv6Numbers = ipv6List.getNumbers();
		for(int i = 0; i < ipv6Numbers.size(); i++) {
			Number ipv6Number = ipv6Numbers.get(i);
			System.out.print(ipv6Number);
			if(i < ipv6Numbers.size() - 1) {
				System.out.print(":");
			}
		}
		
		System.out.println("\n====================================================\n");
		
		// 피타고라스
		Numeric a = new Numeric(3);
		Numeric b = new Numeric("4");
		Numeric c = a.square().add(b.square()).root();
		System.out.println(c);
		
		System.out.println("\n====================================================\n");
		
		// 숫자 판별
		List<Object> numbers = new ArrayList<>();
		numbers.add(2);
		numbers.add("7");
		numbers.add(new Numeric(-3));
		
		Object o = new Object[][] {
			{1, "10", "-3"},
			{7, -1, new Object[] {"7", 9, "-2"} },
			{numbers}
		};
		System.out.println(Numerics.areRealNumbers(o));
		System.out.println(Numerics.areIntegers(o));
		System.out.println(Numerics.areNaturalNumbers(o));
	}
}