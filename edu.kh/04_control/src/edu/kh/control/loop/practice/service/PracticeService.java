package edu.kh.control.loop.practice.service;

import java.util.Scanner;

public class PracticeService {
	Scanner sc = new Scanner(System.in);
	
	public void practice1() {
		System.out.print("1이상의 숫자를 입력하세요 : ");
		int num = sc.nextInt();
		if(num>0) {
			for(int i = 1; i<=num; i++) {
			System.out.print(i+" ");
			}
		}else {
			System.out.println("1 이상의 숫자를 입력해주세요.");
		
		}
	}
	
	
	public void practice3() {
		System.out.print("정수를 하나 입력하세요 : ");
		int num = sc.nextInt();
		int sum = 0;
		for(int i=1; i <= num-1; i++) {
			System.out.print(i+" + ");
			sum += i;
		}
		System.out.println(num+" = " + (sum+num));
	}
	
	
	public void practice4() {
		System.out.print("첫 번째 숫자  : ");
		int num1 = sc.nextInt();
		System.out.print("두 번째 숫자 : ");
		int num2 = sc.nextInt();
		
		if(num1>=1 && num2>=1) {
			if(num1>num2) {
				for(int i= num2; i<=num1; i++) {
					System.out.print(i + " ");
				}
			}else if(num1<num2){
				for(int i= num1; i<=num2; i++) {
					System.out.print(i + " ");
				}
			}else {
				System.out.println("두 값이 같습니다 다시 입력해주세요");
			}
		  
		}else {
			System.out.println("1 이상의 숫자를 입력해주세요");
		}
	}
	
	public void practice8() {
		System.out.print("정수  : ");
		int num1 = sc.nextInt();
		for(int j = 0; j<=num1; j++) {
		for(int i = num1; i>j; i--) {
			System.out.print("*");
			}
		System.out.println();
		}
	}
	public void practice10() {
		for(int i=0; i<5; i++) {
			for(int j = 4; j >=i; j--) {
				System.out.print(" ");
			}for(int f = 1; f<=i*2+1; f++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void practice6() {
		System.out.print("정수  : ");
		int num1 = sc.nextInt();
		if(num1<=9) {
			for(int i = num1; i<10; i++) {
				System.out.println("===="+i+"단====");
			for(int j = 1; j<10; j++) {
				System.out.println(i+"*"+j+"="+i*j);
			}
		}
		}else {
			System.out.println("9이하 숫자를 입력해주세요");
		}
	}
}
