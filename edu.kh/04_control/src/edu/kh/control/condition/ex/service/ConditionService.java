package edu.kh.control.condition.ex.service;

import java.util.Scanner;

public class ConditionService {

//	제어문
//	 프로그램의 흐름을 바꾸는 (제어하는) 역할을 하는 코드
	
//	조건문
//	 제어문중 하나로 조건에 따라 다른 문장(코드)가 실행되도록 하는 코드
	
//	if문 예제
	public void example1() {
//		단독 if문
//		if(조건식) {
//		조건식이 ture일경우 수행할 코드
//		}
//
//		
//		if - else
//		if(조건식) {
//			조건식이 ture일경우 수행할 코드
//		}else{
//			조건식이 false일경우 수행할 코드
//		}	
//		
//		
//		if - else if - else 문
//		 조건이 다수일 경우 사용하는 조건문
//		if(조건식1){
//		조건식 1이 true일 경우 수행
//		}else if (조건식2){
//		조건식2가 true일 경우 수행
//	 	}else{
//		앞선 조건식을 모두 만족하지 못하는 경우 수행
//		}
		
//		입력받은 정수가 양수인지 아닌지 확인
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력: ");
		int input = sc.nextInt();
		if(input>0) {
			System.out.println("양수입니다.");
		}else {
			System.out.println("양수가 아니다.");
		}
		
//		if(input <= 0) {
//			System.out.println("양수가 아니다");
//		}
		
	}
	public void example2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요: ");
		int input = sc.nextInt();
		if(input%2==0) {
			System.out.println("짝수입니다.");
		}else {
			System.out.println("홀수입니다.");
	    }
	}
	public void example3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력1 : ");
		int num1 = sc.nextInt();
		System.out.print("정수 입력2 : ");
		int num2 = sc.nextInt();
		if(num1>num2) {
			System.out.println(num1 + "은 " + num2+"보다 크다");
		}else {
			System.out.println(num1 + "은 " + num2 + "보다 작거나 같다");
		}
		
		
	}
		
	public void example4() {
//		입력 받은 정수가 양수 , 음수 , 0 중 어떤 것인지 확인
		
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 입력: ");
		int input = sc.nextInt();
		
		if(input > 0) {
			System.out.println("양수");
		}else if(input < 0){
			System.out.println("음수");
		}else {
			System.out.println("0");
		}
	}
	public void example5() {
		Scanner sc = new Scanner(System.in);
		System.out.print("1~12사이 숫자를 입력해주세요: ");
		int month = sc.nextInt();
		
		String result = null;//아래 if문을 통해 결정된 출력 내용을 저장할 변수선언
		
		if(month >= 3 && month <= 5) {
			result = "봄";
		}else if(month >= 6 && month <= 8) {
			result = "여름";
		}else if(month >= 9 && month <= 11) {
			result = "가을";
		}else if(month == 12 || month == 1 ||month == 2 ){
			result = "겨울";
		}else {
			result = "잘못 입력하셨습니다.";
		}
			System.out.println(result);
		
		
	
	}

	public void example6() {
//		나이를 입력받아
//		13이하는 어린이, 19세 이하는 청소년, 19세 초과는 성인으로 출력
		
		Scanner sc = new Scanner(System.in);
		System.out.println("나이 입력: ");
		
		int age = sc.nextInt();
		
		String result = null;
		
		if(age <= 13) {
			result = "어린이";
		}else if(age <= 19) {
			result = "청소년";
		}else {
			result = "성인";
		}
		System.out.println(result);
		
	}
	public void example7() {
		Scanner sc = new Scanner(System.in);
		System.out.println("나이 입력: ");
		
		int age = sc.nextInt();
		String result = null;
		
		if(age > 0) {
			if(age <= 13) {
				result = "어린이";
			}else if (age <= 19) {
				result = "청소년";
			}else {
				result = "성인";
			}
			
			
		}else  {
		result 	= "잘못 입력하셨습니다.";
		}
		System.out.println(result);
	}
//	switch문
//	식 하나로 많은 경우의 수를 처리할 경우 사용
//	식의 결과 값과 일치하는 case문을 수행
//	switch(식){
//	case 결과값1:수행코드 1; break;
//	case 결과값2:수행코드 2; break;
//	case 결과값3:수행코드 3; break;
//	}
//	switch문에서 case문 사이에 break가 존재하지 않으면 다음 case문을 실행함
	
	public void example8() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력: ");
		int input = sc.nextInt();
		String result = null;
//		if(input == 1) {
//			result = "빨간색";
//		}else if(input == 2) {
//			result = "주황색";
//		}else if (input == 3) {
//			result = "노랑색";
//		}else {
//			result = "잘못 입력하셨습니다";
//		}
		switch(input) {
		case 1 : result = "빨간색"; break; 
		case 2 : result = "주황색"; break; 
		case 3 : result = "노랑색"; break; 
		default : result = "잘못 입력하셨습니다.";
		}
		System.out.println(result);
	}
	public void example9() {
		Scanner sc = new Scanner(System.in);
		System.out.print("1~12사이 숫자를 입력해주세요: ");
		int month = sc.nextInt();
		
		String result = null;
		
		switch(month){
		
		case 3 : case 4 :case 5 : result = "봄";break;
		case 6 : case 7 :case 8 : result = "여름";break;
		case 9 : case 10 :case 11 : result = "가을";break;
		case 12 : case 1 :case 2 : result = "겨울";break;
		default : result = "잘못입력하였습니다.";
		}
		System.out.println(result);
	}
	public void example10 () {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력1 : ");
		int num1 = sc.nextInt();
		System.out.print("정수 입력2 : ");
		int num2 = sc.nextInt();
		System.out.print("연산자 : ");
		char op = sc.next().charAt(0);
		int result = 0;
		switch(op) {
		case '-' : result = num1 - num2;break;
		case '+' : result = num1 + num2;break;
		case '*' : result = num1 * num2;break;
		case '/' : result = num1 / num2;break;
		case '%' : result = num1 % num2;break;
		}
		System.out.printf("%d %c %d = %d \n",num1, op, num2, result);
		
		
				
	}
	
	
}
