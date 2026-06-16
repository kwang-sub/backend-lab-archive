package edu.kh.api.ex.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.sound.midi.SysexMessage;

import edu.kh.api.ex.model.vo.Student;

public class APIService {
	public void example1() {
		Student std1 = new Student("홍길동", 3, 2);
		Student std2 = new Student("홍길동", 3, 5);
		System.out.println(std1);
		System.out.println(std1.equals(std2));
		
		System.out.println(std1.hashCode());
	}
	
	
	
	public void example2() {
//		String 리터럴로 생성된 문자열은 String Pool이라는 Heap영역 공간에 생성됨.
		
		Scanner sc = new Scanner(System.in);
		
		String str1 = "abc";
		String str2 = "abc";
		String str3 = new String("abc");
		
		System.out.print("abc 입력 : ");
		String str4 = sc.next();
		
		System.out.println(str1 == str2);
//		String 리터럴로 "abc"가 초기화된 str1, str2는 String Pool에 있는 같은 "abc"의 주소를 참조한다.
		
		System.out.println(str1 == str3);
		System.out.println(str1 == str4);
		System.out.println("std1 : " + System.identityHashCode(str1));
		System.out.println("std2 : " + System.identityHashCode(str2));
		System.out.println("std3 : " + System.identityHashCode(str3));
		System.out.println("std4 : " + System.identityHashCode(str4));
		
//		System.identityHashCode()를 사용하여 str1과 str2가 참조하는 주소가 실제로 같은지 확인한다 하지만 자바는 실제 메모리 주소를 볼수는 없지만 메모리 주소를 이용해 일정한 형태에  숫자로 변환하여 보여준다.
//		
		
	}
	public void example3() {
//		String의 불변성
//		String 자료형의 값은 한번 메모리에 저장되면 절대로 변할 수 없다.
//		
//		String 불변성으로 인해 발생하는 문제점은 많은 양의  String 객체가 반복적으로 이어쓰기, 생성등이 이루어지게 되면 그 때 마다 새로운 String 객체가 생성되기 떄문에 심한 메모리 소비를 야기한다.
		
		String str1 = "abc";
		System.out.println("def 추가전 : " + System.identityHashCode(str1));
		str1 += "def";
		System.out.println(str1);
		System.out.println("def 추가후 : " + System.identityHashCode(str1));
		
//		String result = "";
//		for(int i = 0; i<10000; i++) {
//			result += i;
//			System.out.println(result);
//		}
	}
	public void example4() {
//		String 불변성 문제 해결
//		StringBuffer, StringBuilder(가변성) : 문자열 값이 변해도 같은 객체 내에서 변화가 이루어진다. 두 클래스는 StringBuffer는 동기화를 지원하고 StringBuilder는 지원하지 않는다는 차이점만 있고 기능은 동일하다.
//		Buffer : 컴퓨터가 데이터를 저장하는 메모리 영역
		StringBuffer sb1 = new StringBuffer();
		System.out.println("abc 추가전 용량 : " + sb1.capacity());
		System.out.println("abc 추가전 주소 : " + System.identityHashCode(sb1));
		sb1.append("abc"); //append(값) : 문자열 제일 뒤에 추가
		System.out.println("abc 추가후 용량 : " + sb1.capacity());
		System.out.println("abc 추가후 주소 : " + System.identityHashCode(sb1));
		sb1.append("defghijklmnopqrstuvwxyz");
		System.out.println(sb1.toString());
		System.out.println("abc 추가후 용량 : " + sb1.capacity());
		
		
		StringBuilder sb2 = new StringBuilder(5);
		sb2.append("Hello Word!!");
		System.out.println("abc 추가후 용량 : " + sb2.capacity());
		System.out.println(sb2.toString());
		
		System.out.println("abc 추가후 주소 : " + System.identityHashCode(sb1));
		
	}
	public void example5() {
//		boolean contains(CharSequence s) : 특정 단어가 문자열에 폼함되어 있는지 확인
		
		String str1 = "Hello World";
		System.out.println(str1.contains("World"));
		
//		String indexOf(String str) : 특정 문자열이 시작하는 인덱스를 반환 없으면 -1을 반환하고 중복되는 문자열이 있다면 앞에서 검색해서 첫번째인덱스를 반환한다
		String str2 = "abcdef abcdef";
		System.out.println(str2.indexOf("c"));
		System.out.println(str2.lastIndexOf("c"));
		System.out.println(str2.indexOf("z"));
		
//		String[] split(String 구분자) : 구분자를 기준으로 하나의 문자열을 분리하여 String[] 형태로 반환한다
		String str3 = "사과,바나나,포도,파인애플,복숭아,멜로,오렌지";
		String[] arr = str3.split(",");
		for(String s : arr) {
			System.out.println(s);
		}
		
		
//		join(CharSequence delimiter, CharSequence... elements) : 배열을 구분자를 이용하여 하나의 String 으로 변환
		
		String str4 = String.join("/", arr);
		
		System.out.println(str4);
		
//		StringTokenizer : 문자열을 지정한 구분자로 쪼개서 하나씩 순서대로 반환하는 객체(구분자 지정하지 않을시 공백이 기준이 된다.) split()보다 구분자를 다양하게 지정할수 있고 속도도 빠르다
		
		String str5 = "aa bb cc";
		StringTokenizer st1 = new StringTokenizer(str5);
		
			//다음 토큰이 있는지 검사하는 메소드
		while(st1.hasMoreTokens()) {
			System.out.println(st1.nextToken());
			
		}
		String str6 = "AA/BB,CC~DD";
		StringTokenizer st2 = new StringTokenizer(str6,"/,~");
		while(st2.hasMoreTokens()) {
			System.out.println(st2.nextToken());
		}
		
	}
	public void example6() {
//		날짜 관련 클래스 Date :많은 생성자, 메소드가 더이상 사용되지 않는 옛날 클래스지만 DB Date와 관계가 있다.
//		Date(); : 기본생성자로 현재시간을 알려준다.
		Date date1 = new Date();
		System.out.println(date1);
		
//		Date(long date) : 1970년 1월 1일 00:00 GMT를 기준으로 몇 밀리세컨드 지난 시간이 객체에 기록됨. 밀리세컨드(ms) = 1/1000초
		Date date2 = new Date(0L);
		System.out.println(date2);
//		우리나라 기준으로는 1970/1/1 09:00출력된다
		Date date3 = new Date(1000L*60*60*24);
		System.out.println(date3);
//		현재 시간으로 부터 24시간 전 출력
		Date date4 = new Date(System.currentTimeMillis() -(1000L*60*60*24) );
		System.out.println(System.currentTimeMillis());
		System.out.println(date4);
		
//		SimpleDateFormat : 날짜를 원하는 형식으로 간단히 변경하는 기능을 제공
		
		SimpleDateFormat sdf = new SimpleDateFormat("G yyyy년 MM월 dd일 E요일 HH시 mm분 ss초");
		String text = sdf.format(date4);
		System.out.println(text);
		
		
	}
	public void example7() {
//		Math 클래스 : 모든 필드와 메소드는 static이다.그러므로 별도의 객체생성을 하지 않는다.
		double d1 = 360.45;
		int ran = (int)(Math.random()*50+1);
		System.out.println(Math.ceil(d1*10)/10);
		System.out.println(Math.round(d1));
		System.out.println(Math.floor(d1));
		System.out.println(ran);
		
	}
	public void example8() {
//		Wrapper클래스 : 기본자료형을 객체 형태로 변환하는 클래스를 지칭한다
//		
//		사용이유
//		각 자료형만의 필요한 필드, 기능을 제공하기 위해서이다.
//		ex) Integer.MAX_VALUE; : int 최대값
//			Integer.parseInt("123"); : String를 int로 변환
//		
//		컬렉션에 변수값을 저장할 수 있게 하기 위해서 이다.
//		
//		
//		기본자료형             Wrapper 클래스
//		byte			Byte
//		short	  		Short
//		int				Integer
//		long 			Long
//		float			Float
//		double			Double
//		char			Character
//		boolean 		Boolean
		
		System.out.println(Integer.BYTES);
		System.out.println(Integer.SIZE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.TYPE);
		
		int iNum = Integer.parseInt("10000");
		System.out.println(iNum+10000);
		
		long lNum = Long.parseLong("10000");
		
		double dNum = Double.parseDouble("3.12345678901234567890");
		double fNum = Float.parseFloat("3.1234567890123456790");
		System.out.println(dNum);
		System.out.println(fNum);
		
		
		int num =123;
		String str = Integer.valueOf(num).toString();
		
//		Boxing : 기본자료형 -> 객체로 변경
		Integer i1 = new Integer(10);
		Integer i2 = new Integer("20");
		
		System.out.println(i1.toString());
		System.out.println(i2);
		
//		unBoxing : Wrapper 객체 -> 기본자료형
		int u1 = i1.intValue();
		int u2 = (int)i2;
		
//		autoBoxing / AutoUnBoxing
//		자동으로 기본 자료형 <->Wrapper 객체 형변환
//		Java9 버전 이후부터는 AutoBoxing/AutoUnBoxing이 강제됨
	}
	
}
