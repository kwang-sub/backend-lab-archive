package edu.kh.control.loop.ex.service;

import java.util.Scanner;

public class LoopService {

//	for문
//	for(초기식; 조건식; 증감식){
//	반복 수행할 코드
//	}
//	
//	초기식 : for문 내에서 반복을 제어하기 위해사용하는 변수를 작성하는 부분
//	조건식 : for문의 반복 여부를 결정하는 부분
//	증감식 : 초기식에 작성된 변수를 for문이 종료할 때 마다 증가 또는 감소 시키는 부분
//	
//	1.초기식에 작성된 변수 생성(int i)
//	2.작성된 변수의 값을 조건식에 대입했을때 true인지 확인
//	3.(조건식이 true일 경우) for문 내부 코드를 1회 실행
//	4.증감식 1회 수행 (i++)
//	5.증감식으로 변화된 값 i를 조건식에 대입
//	->true일 경우 2~5번 반복, false일 경우 종료
	public void example1() {
		
		for (int i =1; i <= 10; i++) {
			System.out.println(i+ "출력");
		}
	}
	    
	public void example2() {
		for (int i = 5; i>0; i--) {
			System.out.println(i+"번째");
		}
	}
	public void example3() {
		for (int i=1; i<21; i+=2) {
			System.out.print(i+" ");
		}
	}
	public void example4() {
		for (double i=1; i<11; i+=0.5) {
			
			System.out.print(i+" ");
		}
	}
	public void example5() {
		for (char ch = 'A'; ch<='Z'; ch++) {
			System.out.print(ch + " ");
		}
	}
	public void example6() {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		for (int i=1; i<=5; i++) {
			System.out.print("입력"+i+" : " );
			sum += sc.nextInt();
			
		}
		
		System.out.printf("평균: %.1f \n", sum/5.0 );
		
		
	}
	public void challenge1() {
//		2~9사이 정수를 입력받아 입력받은 수의 구구단출력
		
		Scanner sc = new Scanner(System.in);
		System.out.print("2~9사이 정수 입력 : ");
		int num = sc.nextInt();
		if(num<10 && num>1) {
		for(int i = 1 ; i < 10 ; i++) {
			System.out.println(num+"x"+i +" = " + num*i);
		    }
		}	else {
			System.out.println("잘못 입력하셨습니다");
			}
		
	}
	public void example7() {
//		java.lang.Math - 수학과 관련된 기능을 제공하는 클래스
//		(java.lang 패키지는 컴파일 시 자동으로 import 된다)
//		Math.random() 메소드 - 난수를 발생시키는 기능 (0.0 <= 난수 < 1.0)
		for(int i = 1; i<11; i++) {
		int random =(int) (Math.random()*10+1);
		System.out.println(random);
		}
	}
	
	public void example8() {
		int sum = 0;
		for(int i = 1; i<6; i++) {
			int random =(int) (Math.random()*20+1);
			System.out.println(random);
			sum += random;
		}
		System.out.println("합계" + sum);
	}
	public void example9() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int num1 = sc.nextInt();
		System.out.print("정수 입력 : ");
		int num2 = sc.nextInt();
		
		int sum = 0;
	
		if(num1<num2) {
			for(int i= num1; i<=num2; i++) {
				sum+=i;
			}
		System.out.println("합계" + sum);
		}else {
			System.out.println("입력1이 입력2가 같거나 작습니다");
		}
	
	}
	public void example10() {
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 : ");
		String in1 = sc.next();
		sc.nextLine();
		System.out.print("입력 : ");
		String in2 = sc.nextLine();
		
		if(in1.equals(in2)) {
			System.out.println("같음");
		}else {
			System.out.println("다름");
		}
	}
	public void example11() {
		for(int min = 0; min < 60; min++) {
			
			for(int sec = 0; sec < 60; sec ++) {
			System.out.printf("%02d분 %02d초\n",min,sec);	
			}
		}

	
	}
	public void example12() {
		for(int i = 0; i < 5; i++) {
			for(int j = 1; j <= 5; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
	public void example13() {
		for(int i = 0; i<3; i++) {
			for(int j = 5; j>=1; j--) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
	public void example14() {
		for(int i=2; i<10; i++) {
			System.out.println("======"+i+"단======");
			for(int j = 1; j<10; j++) {
				System.out.println(i+"x"+j+"="+i*j);
			}
	    }
	}
	public void example15() {
		for(int i=9; i>1; i--) {
			System.out.println("======"+i+"단======");
			for(int j = 9; j>0; j--) {
				System.out.println(i+"x"+j+"="+i*j);
			}
	    }
		
		
	}
	public void example16() {
		for(int i = 1; i<=4; i++) {
			for(int j = 1; j<=i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
	public void example17() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력 : ");
		int num = sc.nextInt();	
		for(int i = 1; i<=num; i++) {
			for(int j = 1; j<=i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}	
	}
	
//	while문
//	while(조건식){
//	조건식이 true일 경우 수행할 코드
//	}
//
////	for문     : 초기식에서 선언된 변수를 이용하여 반복을 제어 할수 있음
////	while문: 별도의 초기식이 존재하지 않아도 되고 반복 종료 조건을 자유롭게 설정 가능
	public void example18() {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자 입력 : ");
		int num = sc.nextInt();
		int input = 0;
		while(input != 9) {
		System.out.println("----------");
		System.out.println("1.조회");
		System.out.println("2.작성");
		System.out.println("3.수정");
		System.out.println("4.삭제");
		System.out.println("9.종료");

		System.out.print("선택>> ");
		input = sc.nextInt();
		
		}
		System.out.println("프로그램 종료");
	}
	public void example19() {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int input = -1;
		
		while(input != 0) {
		
			System.out.print("정수입력 : ");
			input = sc.nextInt();
			sum += input;	
		}
	 System.out.println("합계 : "+sum);
	}
//	do ~ while문
//	do{작성한코드 한번은 무조건 실행}
//	do {
//		반복수행할코드작성
//	}while(조건식);
	public void example20() {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int input = 0;
		do {
			System.out.println("입력 : ");
			input = sc.nextInt();
			sum += input;
		}while(input != 0);
		System.out.println("합계: "+ sum);
	}


}
