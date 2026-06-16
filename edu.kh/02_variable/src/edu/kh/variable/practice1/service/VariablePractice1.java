package edu.kh.variable.practice1.service;

import java.util.Scanner;

public class VariablePractice1 {
//	실습문제1
	
	public void practice1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String inputName = sc.next();
		System.out.print("성별을 입력하세요(남자/여자): ");
		String inputGender = sc.next();
		System.out.print("나이를 입력하세요: ");
		int inputAge= sc.nextInt();
		System.out.print("키를 입력하세요(cm): ");
		double hight = sc.nextDouble();
		
		System.out.println("키"+hight+"cm인 "+inputAge+"살 "+inputGender+
				" "+inputName+"님 반갑습니다^^");
		
		
	}
	
	
//	실습문제2
	
	
	public void practice2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("첫 번째 정수: ");
		int one = sc.nextInt();
		System.out.print("두 번째 정수: ");
		int two = sc.nextInt();
		System.out.println("\n");
		System.out.println("더하기 결과: "+(one+two));
		System.out.println("빼기 결과: "+(one-two));
		System.out.println("곱하기 결과: "+one*two);
		System.out.println("나누기 몫 결과: "+one/two);
		
	}
	
	
//	실습문제3
	
	public void practice3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("가로: ");
		double a = sc.nextDouble();
		System.out.print("세로: ");
		double b = sc.nextDouble();
		System.out.println("\n");
		System.out.println("면적:"+a*b);
		System.out.println("둘레: "+(a+b)*2);
		
	}
}
