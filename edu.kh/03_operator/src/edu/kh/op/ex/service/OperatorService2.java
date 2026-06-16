package edu.kh.op.ex.service;

import java.util.Scanner;

public class OperatorService2 {
	
//	논리연산자
	public void example1() {
//		논리 값 두 개를 비교하는 연산자
//		&&(and) : 논리 값 두 개가 모두 true일 때만 true를 반환
//		-> true && true == true
		
//		||(or) : 논리 값 두 개가 모두  false일 때만 false를 반환(true가 하나라도 있으면 true)
//		-> false || false == false
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 하나 입력: ");
		int input = sc.nextInt();
		
		boolean result = (input>0 && input<101);
		
		System.out.println("입력받은 정수는 1~100사이 숫자인가? :" + result);
	}

	public void example2() {
		
//		입력받은 알파벳이 대문자 A~Z 사이의 문자인지 확인하기
		Scanner sc = new Scanner(System.in);
		System.out.print("알파벳 하나 입력: ");
		char a = sc.next().charAt(0); 
		
		boolean result = (a >= 'A' && a <= 'Z');
		System.out.println("입력받은 알파벳은 A~Z 사이가 맞는가?" + result);
		
	}
	
	public void example3() {
//		입력받은 정수가 0 미만 또는 100 초과인지 확인
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		boolean result = (input < 0 || input >100);
		System.out.println("입력받은 수가 0미만 또는 100 초과인가?" + result);
	}
//	public void example4() {
//		입력받은 정수가 0 미만 또는 100 초과인지 확인
//		Scanner sc = new Scanner(System.in);
//		System.out.print("정수 입력 : ");
//		int input = sc.nextInt();
//		
//		boolean result = !(input >= 0 && input <=100);
//		System.out.println("입력받은 수가 0미만 또는 100 초과인가?" + result);
//	}
// 	입력받은 문자 하나가 'y'인지 확인(대소문자 구분x)
	public void example4() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자하나입력: ");
		char input = sc.next().charAt(0);
		
		boolean result = input == 'y' || input == 'Y';
		System.out.println(result);
		
		
	}	
	public void example5() {
		int num = 10;
		num = num + 10;
		
		int num2 = 10;
		num2+= 10;
	}
	public void example6() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 하나 입력 : ");
		int input = sc.nextInt();
		String result = input>0 ? "양수":"음수";
		System.out.println(input+ "은/는 "+ result+"이다");
	}
	
	public void example7() {
	Scanner sc = new Scanner(System.in);
	System.out.print("정수 하나 입력 : ");
	int input = sc.nextInt();
	char result = input>0 ? '+':(input == 0 ? '0':'-');
			System.out.println(input+ "은/는 "+ result+"이다");
	}
	public void example8() {
		Scanner sc = new Scanner(System.in);
		System.out.print("주민등록번호를 입력해주세요( - 형식으로 작성):");
		char input = sc.next().charAt(7);
		String result = input == '1' ||input == '3'  ? "남자입니다" : (input == '2' || input == '4' ? "여자입니다":"잘못입력하셨습니다");  
			System.out.println(result);
	}
	
		
	
	
	
}
