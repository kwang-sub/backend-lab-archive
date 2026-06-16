package edu.kh.array.ex.service;

import java.util.Scanner;

public class ArrayService1 {
	public void example1() {
	//	1.배열선언 :int[] 참조 변수 arr 선언
		int[] arr;
		
		
		
	//	2.배열 할당
		arr = new int[4];
	//	heap 영역에 새로운 int 자료형 공간 4칸을 할당
	//	새로 생성된 int[]의 시작주소를 arr 참조변수에 대입 arr변수의 자료형은 int[]로 새로 생성된 int[]과 자료형이 일치함 ->대입연산 가능
	
	//	3.배열 초기화 & 사용방법
		arr[0] = 1;
		arr[1] = 10;
		arr[2] = 5;
		System.out.println("arr[0] : "+arr[0]);
		System.out.println("arr[1] : "+arr[1]);
		System.out.println("arr[2] : "+arr[2]);
		System.out.println("arr[3] : "+arr[3]);
		System.out.println(arr);
		for(int i=0; i<4; i++) {
			System.out.printf("arr[%d] : %d\n",i,arr[i]);
		}
		
	}
	public void example2() {
//		for문을 이용한 배열 초기화
//		초기화할 값이 규칙적이라면 for문을 이용하여 초기화 할수있다
		int[] arr = new int[5];
		for(int i =0; i<arr.length; i++) {
		arr[i] = i;
		System.out.println("arr["+i+"] : " + arr[i]);
		}
		
	}
	public void example3() {
		int[] arr2 = new int[7];
		for(int i = 0; i < arr2.length; i++) {
			arr2[i]=i*2;
			
		}
		for(int i= arr2.length-1; i>=0; i-- ) {
			System.out.println("arr["+i+"] : "+ arr2[i]);
		}
	}
	public void example4() {
		Scanner sc = new Scanner(System.in);
		double[] arr = new double[3];
		double sum = 0;
		for(int i = 0; i<arr.length; i++) {
			System.out.print("실수입력 : ");
			double num = sc.nextDouble();
			arr[i] = num;
			sum += arr[i];
		}
		for(int i = 0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println("합계 : "+sum);
	}
	public void example5() {
		int[] arr = {1,2,3,4,5};
		System.out.println(arr[2]);
		System.out.println(arr[4]);
		
		String[] fruits = {"딸기","바나나","사과","오렌지","토마토","아보카도"};
		System.out.println("fruits.length : "+ fruits.length);
		System.out.println(fruits[2] + fruits[5] + "쥬스");
	}
	public void challenge1() {
		Scanner sc = new Scanner(System.in);
		int[] num =new int[5];
		for(int i=0; i<num.length; i++) {
			System.out.print("정수입력 : ");
			int in =sc.nextInt();
			num[i]=in;
		}
		while(true) {
			System.out.print("1~5사이 숫자 하나 입력  : ");
			int input =sc.nextInt();
			if(input<6&&input>0) {
				System.out.println(input + "번쨰 입력한 수 : "+num[input-1]);
				break;
			}else {
				System.out.println("값이 안 맞습니다");
			}
		}
	}
	public void example6() {
		Scanner sc = new Scanner(System.in);
		double[] arr = new double[5];
		double sum = 0;
		for(int i=0; i<arr.length; i++) {
			System.out.print("키 입력 : ");
			arr[i] = sc.nextDouble();
		}
		double max = arr[0];
		double min = Double.MAX_VALUE;
		for(int i =0; i<arr.length; i++) {
			sum += arr[i];
			if(arr[i]>max) {
				max = arr[i];
			}
			if(arr[i]<min) {
				min = arr[i];
			}
		}
		System.out.println("평균 : " + sum/arr.length);
		System.out.println("최장신 : " + max);
		System.out.println("최단신 : " + min);
	}
	public void example7() {
		Scanner sc = new Scanner(System.in);
		System.out.println("인원수 : ");
		int num = sc.nextInt();
		int[] arr = new int[num];
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d번째 인덱스 입력: ",i);
			arr[i] = sc.nextInt();
		}
		for(int i = arr.length-1;i>-1; i-- ) {
			System.out.print(arr[i]+ " ");
		}
	}
	public void example8() {
//		배열단점: 한번 크기가 정해지면 바꿀수 없고 한 가지 자료형만 저장
		int[] arr= new int[3];	
		System.out.println("arr 배열의 길이 : "+arr.length);
		arr = new int[6];
		System.out.println("arr 배열의 길이 : "+arr.length);
		
	}
//	String은 char가 여러개 모여있는 문자배열의형태이다
//	char와 같지는 않다
//	배열과 비슷하게 사용할수있다
	public void example9() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 입력 : ");
		String str = sc.nextLine();
		
		System.out.print("검색할 문자 : ");
		char ch = sc.next().charAt(0);
		int count=0;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i)==ch) {
				count++;
			}
		}System.out.printf("%s는 %s가 %d번 작성됐습니다",str,ch,count);
	}
	
}
