package edu.kh.control.branch.ex.service;

import java.util.Scanner;

public class BranchService {
	Scanner sc = new Scanner(System.in);
	
//	분기문           : 가장 가까운 반복문에만 영향을 미침
//	break    : 반복문을 종료시키는 구문
//	contiune : 반복문 내에서만 사용 가능하며 수행 시 for문에서는 증감식으로 while문에서는 다음 반복으로 건너 뜀 
	public void example1() {
		int sum = 0;
		
		
		while(true) {
			System.out.print("정수입력 : ");
			int num = sc.nextInt();
			sum += num;
			if(num == 0) {
				break;
			}
		}
		System.out.println("합계 : "+sum);
	}
	
	public void example2() {
		String result = "";
		
		while(true) {
			System.out.print("단어입력 : ");
			String str = sc.nextLine();
			if(str.equals("end")) {
				break;
			}
		
			result += str+" ";
			
		}
		System.out.println("결과 : "+result);
	}
	public void example3() {
		int sum = 0;
		
		for(int i = 1; i<101; i++) {
			if(i%3==0) {
				continue;
			}
				sum += i;
		}
		System.out.println("합계 : "+sum);
	}
	public void example4() {
		for(int i= 1; i<10; i++) {
			if(i%2==0) {
				continue;
			}
			System.out.println("===="+i+"단====");
			for(int j=1; j<10; j++) {
				if(j%2==1) {
					continue;
				}System.out.println(i+" * "+j+" = " +i*j);
				
			}
		}		
	}
}
