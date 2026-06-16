package edu.kh.array.practice.service;

import java.util.Arrays;
import java.util.Scanner;

public class PracticeService {

	public void practice1() {
		int[] arr = new int[9];
		int sum =0;
		for(int i =0; i<arr.length; i++) {
			arr[i]=i+1;
			System.out.print(arr[i]+" ");
			if(i%2==0) {
				sum+=arr[i];
			}
		}
		System.out.println();
		System.out.println("짝수 번째 인덱스 합 :"+sum);
	}
	public void practice2() {
		int[] arr = new int[9];
		int sum =0;
		for(int i =arr.length-1; i>=0; i--) {
			arr[i]=i+1;
			System.out.print(arr[i]+" ");
			if(i%2==1) {
				sum+=arr[i];
			}
		}
		System.out.println();
		System.out.println("홀수 번째 인덱스 합 :"+sum);
		
	}
	public void practice3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("양의 정수 : ");
		int in = sc.nextInt();
		int[] arr = new int[in];
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+1;
			System.out.print(arr[i]+" ");
		}
	}
	public void practice4() {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[5];
		for(int i=0; i<arr.length; i++) {
			System.out.print("입력"+i+" : ");
			arr[i] = sc.nextInt();
		}
		System.out.print("검색할 값 : ");
		int re = sc.nextInt();
		for(int i=0; i<arr.length; i++) {
		if(arr[i]==re) {
			System.out.println("인덱스 : "+i);
			}
		}
	}
	public void practice5() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 : ");
		String str =sc.next();
		System.out.print("문자 : ");
		char ch = sc.next().charAt(0);
		String result="";
		int count = 0;
		for(int i =0; i<str.length(); i++) {
			if(str.charAt(i)==ch) {
				result+= i+" ";
				count++;
			}
		}
		System.out.println(str+"에 "+ch+"가 존재하는 위치(인덱스) : "+result );
		System.out.println(ch+" 개수 : "+count);
	}
	public void practice6() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int in = sc.nextInt();
		int[] arr = new int[in];
		int sum = 0;
		for(int i = 0; i<arr.length; i++) {
			System.out.print("배열 "+i+"번째 인덱스에 넣을 값 : ");
			int input=sc.nextInt();
			sum+=input;
			arr[i]=input;
			
		}
		for(int i = 0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		System.out.println("총합 : "+sum);
	}
	public void practice7() {
		Scanner sc = new Scanner(System.in);
		System.out.print("주민등록번호(-포함) : ");
		String str = sc.nextLine();
		
		for(int i= 0; i<str.length(); i++) {
			if(i<8) {
				System.out.print(str.charAt(i));
			}else{
				System.out.print("*");
			}
		}
	}
	
//	메소드 명 : public void practice8(){}
//	3이상인 홀수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고,
//	중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요.
//	단, 입력한 정수가 홀수가 아니거나 3 미만일 경우 “다시 입력하세요”를 출력하고
//	다시 정수를 받도록 하세요.
	public void practice8() {
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.print("정수 : ");
			int in = sc.nextInt();
			if(in<3 || in%2==0) {
				System.out.println("3이상의 홀수 입력해주세요");
			}else {
			
				int[] num = new int[in];
				if(in >=3) {
					for(int i = 0; i<num.length; i++ ) {
						num[i] = i+1;
						System.out.print(num[i]+",");
					}
					
					for(int i= num.length-2; i>=1; i--) {
						System.out.print(num[i]+",");
					}
					System.out.println(num[0]);
				}
				break;
			}
			
		}
		
	
	
	}
	public void practice9() {
		System.out.print("발생한 난수 : ");
		int[] arr = new int[10];
		for(int i =0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*10+1);
			
			System.out.print(arr[i]+" ");
		}
		
		
	}
	public void practice10() {
		System.out.print("발생한 난수 : ");
		int[] arr = new int[10];
		int max = 0;
		int min = arr.length;
		for(int i =0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*10+1);
			
			System.out.print(arr[i]+" ");
		}
		for(int i=0; i<arr.length; i++) {
			if(arr[i]>max) {
				max=arr[i];
			}
		}
		for(int i=0; i<arr.length; i++) {
			if(arr[i]<min) {
				min=arr[i];
			}
		}
		System.out.println();
		System.out.println("최대값 : "+max);
		System.out.println("최소값 : "+min);
		
	}
	
	
}
