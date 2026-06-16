package edu.kh.control.condition.practice.service;

import java.util.Scanner;

public class PracticeService {
	public void practice1() {
		
		System.out.println("1.입력");
		System.out.println("2.수정");
		System.out.println("3.조회");
		System.out.println("4.삭제");
		System.out.println("7.종료");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("메뉴 번호를 입력하세요 : ");
		int num = sc.nextInt();
		String result = null;
	switch(num){
		case 1 : result = "입력 메뉴입니다"; break;
		case 2 : result = "수정 메뉴입니다"; break;
		case 3 : result = "조회 메뉴입니다"; break;
		case 4 : result = "삭제 메뉴입니다"; break;
		case 5 : result = "종료 메뉴입니다"; break;
	}
		System.out.println(result);
	}


	public void practice2() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 한개 입력하세요 : ");
		int num =sc.nextInt();
		
		if(num%2==1 && num >-1) {
			System.out.println("홀수입니다.");
		}else if(num%2==0  && num >-1) {
			System.out.println("짝수입니다.");
		}else {
			System.out.println("양수만 입력하세요");
		}
	}
	public void practice3() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("국어점수 : ");
		int ko = sc.nextInt();
		
		System.out.print("수학점수 : ");
		int ma = sc.nextInt();
		
		System.out.print("영어점수 : ");
		int en = sc.nextInt();
		
		int sum = ko+en+ma;
		int avg =sum/3;
		
		if(ko>=40 && ma>=40 && en>=40 && avg >= 60 ) {
			System.out.println("국어 : "+ko);
			System.out.println("수학 : "+ma);
			System.out.println("영어 : "+en);
			System.out.println("합계 : "+sum);
			System.out.println("평균 : "+sum/3);
			System.out.println("축하합니다, 합격입니다!");
		}else {
			System.out.println("불합격입니다.");
		}
	}
	public void practice4() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~12 사이의 정수 입력 : ");
		int num = sc.nextInt();
		
		int day = 0;
		
		if(num>0 && num<13) {
		switch(num) {
		case 1 : case 3 : case 5 : case 7 : case 10 : case  12 : day = 31;break;
		case 4 : case 6 : case 8 : case 9 : case 11 : day = 30;break;
		case 2 : day = 28;break;
		
		}
		System.out.println(num + "월은 " + day + "일 까지 있습니다.");
		}else {
			System.out.println(num +"월은 잘못 입력된 달입니다.");
		}
	}

	public void practice5() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("키(m)를 입력해 주세요 : ");
		double ht = sc.nextDouble();
		
		System.out.print("몸무게(kg)를 입력해 주세요 : ");
		double wt = sc.nextDouble();
		
		double bmi = wt / (ht*ht);
		System.out.println(" BMI 지수: " + bmi);
		if (bmi<18.5) {
			System.out.println("저체중");
		}else if (bmi<23) {
			System.out.println("정상체중");
		}else if(bmi<25) {
			System.out.println("과체중");
		}else if(bmi<30) {
			System.out.println("비만");
		}else {
			System.out.println("고도 비만");
		}
	
	
	}
	public void practice6() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("중간 고사 점수 : ");
		double num1 = sc.nextDouble();
		
		System.out.print("기말 고사 점수 : ");
		double num2 = sc.nextDouble();
		
		System.out.print("과제 점수 : ");
		double num3 = sc.nextDouble();
		
		System.out.print("출석 회수 : ");
		double num4 = sc.nextDouble();
		
		
		
		System.out.println("========= 결과 ========");
		double me = num1 * 0.2;
		double fi = num2*0.3;
		double cl = num3*0.3;
		double sum = me+fi+cl+num4;
		
		
		if(num4 < 15) {
			System.out.println("Fail [출석 회수 부족("+num4+"/20)]");
		}
		else if(sum <70 ) {
			System.out.println("중간 고사 점수 (20) : " + me);
			System.out.println("기말 고사 점수 (30): "+ fi);
			System.out.println("과제 점수            (30) : "+ cl);
			System.out.println("출석점수 :   (20) : "+ num4);
			System.out.println("총점 : "+ sum);
			System.out.println("Fail [점수 미달]");
		}else {
			System.out.println("중간 고사 점수 (20) : " + me);
			System.out.println("기말 고사 점수 (30): "+ fi);
			System.out.println("과제 점수            (30) : "+ cl);
			System.out.println("출석점수 :   (20) : "+ num4);
			System.out.println("총점 : "+ sum);
			System.out.println("PASS");
		}
		
	}
	public void practice7() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("피연산자1 입력  : ");
		int num1 = sc.nextInt();
		
		System.out.print("연산자 입력(+,-,*,/,%) : ");
		char ch = sc.next().charAt(0);
		
		System.out.print("피연산자2 입력  : ");
		int num2 = sc.nextInt();
		
		double result = 0;
		String str = null;
		
		if(num1 == 0) {
			switch(ch) {
			case '+' : str = "더할 수 없습니다";break;
			case '-' : str = "뺄 수 없습니다";break;
			case '*' : str = "곱할 수 없습니다";break;
			case '/' : str = "나눌 수 없습니다";break;
			case '%' : str = "나머지 값을 구할 수 없습니다. ";break;
			}
			System.out.println("0은 "+str);
		}else if(!(ch == '+' || ch == '-'|| ch == '*'|| ch == '/'|| ch == '%') ) {
			System.out.println("잘못 입력하셨습니다.");
			
		}else {
			switch(ch) {
			case '+' : result = num1+num2;break;
			case '-' : result = num1-num2;break;
			case '*' : result = num1*num2;break;
			case '/' : result = num1/(double)num2;break;
			case '%' : result = num1%num2;break;
			}
			System.out.printf("%d %c %d = %.6f",num1,ch,num2,result);
		}
		
	}




}








