package edu.kh.homework.practice1.service;

import java.util.Scanner;

public class PracticeService1 {
	
	public void practice1() {
		int iNum1 = 10;
		int iNum2 = 4;
		float fNum = 3.0f;
		double dNum = 2.5;		
		char ch = 'A';
		
		System.out.println(iNum1/iNum2);
		System.out.println((int)dNum);
		
		System.out.println(iNum2*dNum);
		System.out.println((double)iNum1);
		
		System.out.println((double)iNum1/iNum2);
		System.out.println(dNum);
		
		System.out.println((int)fNum);
		System.out.println(iNum1 / (int)fNum);
		
		System.out.printf("%.7f",(iNum1/fNum));
		System.out.printf("%.16f",(double)iNum1/fNum);
//		틀린문제 float형으로표현하면 소수점 8자리까지 표현됨
//		double형은 소수점 16자리까지
		
		System.out.println(ch);
		System.out.println((int)ch);
		System.out.println((int)ch+iNum1);
		System.out.println((char)(ch+iNum1));
	}

	public void practice2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("인원 수: ");
		int people = sc.nextInt();
		
		System.out.print("사탕 개수: ");
		int candy = sc.nextInt();
		
		System.out.println();
		
		System.out.println("1인당 사탕 개수 : " + candy/people);
		System.out.println("남는 사탕 개수 : " + candy%people);
	}
	
	public void practice3() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름: ");
		String name = sc.next();
		
		System.out.print("학년(정수만): ");
		int year = sc.nextInt();
		
		System.out.print("반(정수만): ");
		int cl = sc.nextInt();
		
		System.out.print("번호(정수만): ");
		int num = sc.nextInt();
		
		System.out.print("성별(남학생/여학생): ");
		String sex = sc.next();
		
		System.out.print("성적(소수점 아래 둘째 자리까지): ");
		double record = sc.nextDouble();
		
		System.out.println();
		
		System.out.printf("%d학년 %d반 %d번 %s %s의 성적은 %.2f이다.",year, cl, num, name, sex, record);

	}
	
	public void practice4() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("국어 : ");
		int ko =sc.nextInt();
		
		System.out.print("영어 : ");
		int en =sc.nextInt();
		
		System.out.print("수학 : ");
		int ma =sc.nextInt();
		
		System.out.println();
		System.out.println("합계 : "+(ko+en+ma));
		System.out.println("평균 : "+(double)((ko+en+ma)/3.0));
	}
	
}
