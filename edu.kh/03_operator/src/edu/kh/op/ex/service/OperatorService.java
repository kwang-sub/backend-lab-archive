package edu.kh.op.ex.service;

public class OperatorService {
	
//	논리 부정 연산자(!)는 논리값을 반대로 바꾸는 연산자
	public void example1() {
		System.out.println(true);
		System.out.println();
		
		boolean isTrue = true;
		System.out.println("isTrue: "+isTrue);
		System.out.println("isTrue: "+!isTrue);
	}
	
//	증감연산자(++,--)는 피연산자를 1증가 또는 감소 시키는 연산자
	public void example2() {
		int num = 10;
		num++;
		System.out.println("num: " + num);
//		전위연산은 먼저 1을 증감시킨후 다른 연산 진행
		int a1 = 10;
		int b1 = ++a1;
		System.out.println(a1+"/"+b1);
//		후위연산은 다른 연산 수행후 1을 증감시킴
		int a2 = 10;
		int b2 = a2++;
		System.out.println(a2+"/"+b2);
		int a3=3;
		int b3 = 5;
		int c3 = a3++ + --b3;
		System.out.println(a3);
		System.out.println(b3);
		System.out.println(c3);
	}
	public void example3() {
		int num1 = 5;
		int num2 = 2;
		System.out.println("몫: " + (double)num1/num2);
		System.out.println("나머지: "+num1%num2);
	}
	public void example4() {
//		a == b : a와b는 같다.
//		a != b : a와b는 같지 않다.
//		a > b  : a는b보다 크다.
//		a < b  : a는b보다 작다.
//		a >= b : a는b보다 크거나 같다.
//		a <= b : a는b보다 작거나 같다.
//		비교 연산자의 연산 결과는 무조건 true 또는 false 이다.
		int num1 = 10;
		int num2 = 20;
		
		System.out.println(num1 == num2);
		System.out.println(num1 != num2);
		System.out.println(!(num1 == num2));
		System.out.println((num1 == num2) == true);
	
		System.out.println("\n-------------------\n");
		
		System.out.println(num1 > num2);
		System.out.println(num1 > (num2 - num1));
		System.out.println((num1 > num2) != (num2 >= num1));
		
		System.out.println("\n-------------------\n");
		
//		% == 또는 != 를 이용해서 짝수,홀수,배수 판별
		int a = 5;
		System.out.println("a는 짝수인가?" + (a%2 == 0));
		System.out.println("a는 짝수인가?" + (a%2 != 1));
		
		System.out.println("a는 홀수인가? " + (a%2 == 1));
		System.out.println("a는 홀수인가? " + (a%2 != 0));
	
		System.out.println("a는 5의 배수인가?" + (a%5 == 0));
		
		
	}
	
}
