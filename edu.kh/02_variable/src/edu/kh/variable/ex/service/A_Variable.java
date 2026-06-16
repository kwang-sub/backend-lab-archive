package edu.kh.variable.ex.service;

public class A_Variable {

//	변수를 사용하지 않는 경우
	public void example1() {
		System.out.println("변수 사용 X");
		System.out.println(2*3.141592653589793*10);
		System.out.println(3.141592653589793*10*10);
		System.out.println(3.141592653589793*10*10*20);
		System.out.println(4*3.141592653589793*10*10);
	}
//	변수를 사용하는 경우
	public void example2() {
		double pi = 3.141592653589793;
		int radius = 5;
		int height = 20;
		System.out.println("\n변수 사용 o");
		System.out.println(2*pi*radius);//원의둘레
		System.out.println(pi*radius*radius);//원의넓이
		System.out.println(pi*radius*radius*height);//원기둥의부피
		System.out.println("\n\n\n");
		System.out.println(4*pi*radius*radius);//구의겉넓이
		
		
		
	}
//	변수선언(초기화예제)
	public void example3() {
//		변수선언:메모리공간에 데이터를 저장할 수 있는 공간을 할당 하는 것.
//		->선언된 변수의 종류에 따라 저장할 수 있는 데이터의 형태와 크기가 달라짐.
		
//		직접 작성되거나 변수에 대입되는 갑 자체를 리터럴이라 함
//		자료형마다 리터럴 표기법이다름
//		대입연산자(=)는 = 기준으로 오른쪽에 있는값을 왼쪽 변수에 대입하라는 연산자
		boolean isTrue;//1byte
		isTrue = true;//boolean 자료형의 표기법은 true/false
		
		byte byteNumber;//1byte
		byteNumber = 1;
		
		short shortNumber;//2byte
		shortNumber = 2;
		
		int intNumber;//4byte 정수기본형
		intNumber = 4;
		long longNumber;//8byte
		longNumber = 8L;
		float floatNumber; //4byte
		floatNumber = 1.23f;
		
		double doubleNumber; //8byte 실수기본형
		doubleNumber = 3.14;
		
		char ch;//2byte(자바),문자하나
		ch = 'a'; //''홀따옴표 기호가 char 자료형의 리터럴 표기법
		String str;//4byte 기본자료형x
		str = "문자열";//""쌍따옴표 기호가 string 자료형의 리터럴 표기법

		System.out.println(isTrue);
		System.out.println(byteNumber);
		System.out.println(shortNumber);
		System.out.println(intNumber);
		System.out.println(longNumber);
		System.out.println(floatNumber);
		System.out.println(doubleNumber);
		System.out.println(ch);
		System.out.println(str);
	}
		
	public void example4() {
		int age = 28;//변수 선언과 동시에 초기화
		final int AGE = 28;//상수 선언과 동시에 초기화
//		final 예약어 :마지막이라는 의미를 가진 예약어
		
		age = 29;
//		AGE = 29; 상수는 값 변경이 불가능하여 에러가 발생
		
		final double PI = 3.141592;
		
	}
	public void example5() {
		String name = "최광섭";//변수선언및 초기화
		int age = 28;
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
		name = "홍길동";
		age = 20;
		System.out.println(name+"님의 나이는 "+age+"세 입니다.");
//		문자열과 다른 자료형 사이에 + 기호는 이어쓰기를 의미한다.
		System.out.println(age+10+"123");
		System.out.println(age+"123"+10);
		System.out.println(age+"10");
		
//		수와 문자열이 혼합되는 경우 사칙연산 순서에 맞게 계산
	}
	
}
