package edu.kh.array2.ex.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Array2Service {
//	이차원 배열 : 자료형이 같은 1차원 배열의 묶음.
//	이차원 배열은 할당된 공간만다 두개의 인덱스 번호를 부여받음
//	앞번호는 행 뒷번호는 열을 표현한다
	
	public void example1() {
//		int[][] arr2;//2차원 배열을 참조할 참조 변수 arr을 선언
//		참조변수 == 참조형 변수 == 레퍼런스 == 레퍼런스변수
//		참조변수는 주소를 입력받기 위해  4byte 공간이 할당됨
		
//		이차원 배열 할당
	int[][] arr2 = new int[4][3];
//	arr[0][0]=0;
//	arr[0][1]=1;
//	
//	arr[1][0]=2;
//	arr[1][1]=3;
//	
//	arr[2][0]=4;
//	arr[2][1]=5;

	
	
//		 int[][] arr2 = {{0,1},{2,3},{4,5}};
	int num = 0;
	for(int col = 0; col<arr2[0].length; col++) {
		for(int row = 0; row<arr2.length;row++) {
			arr2[row][col]=++num;
			
		}
	}
	
//	System.out.print(arr2[0][0]);
//	System.out.println(arr2[0][1]);
//	
//	System.out.print(arr2[1][0]);
//	System.out.println(arr2[1][1]);
//	
//	System.out.print(arr2[2][0]);
//	System.out.println(arr2[2][1]);
	for(int i = 0; i<arr2.length; i++) {
		for(int j =0; j<arr2[i].length; j++) {
			System.out.print(arr2[i][j] + " ");
		}
		System.out.println();
	}
	}
	public void example2() {
		Scanner sc = new Scanner(System.in);
		int[][] arr =new int[2][3];
	
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.printf("arr[%d][%d] 정수입력 : ",i,j);
				arr[i][j]=sc.nextInt();
			}
		}
		for(int i=arr.length-1; i>=0; i--) {
			for(int j=arr[i].length-1; j>=0;j--) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.print("1열에 작성된 값 : ");
		for(int i=0; i<arr.length; i++) {
			
				System.out.print(arr[i][1]+" ");
			
		}
	}
	public void example3() {
		Scanner sc = new Scanner(System.in);
		int[][] arr =new int[3][3];
		for(int i=0; i<arr.length-1; i++) {
			for(int j =0; j<arr[i].length; j++) {
				System.out.printf("arr[%d][%d] 정수입력 : ",i,j);
				arr[i][j]=sc.nextInt();
				arr[2][j]+=arr[i][j];	
			}
		}
//		for(int i=0; i<arr[0].length;i++) {
//			for(int j=0; j<arr.length-1;j++) {
//				arr[2][i]+=arr[j][i];
//			}
//		}
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	public void example4() {
//		가변배열
//		행의 개수는 정해져 있으나 열의 개수가 정해지지 않은 2차원배열
		int [][] arr =  new int [3][];
		arr[0] = new int[3];
		arr[1] = new int[4];
		arr[2] = new int[2];
	      int num = 0; 
	      
	      for(int row=0 ; row<arr.length ; row++ ) { 
	         for(int col=0 ; col<arr[row].length ; col++) {
	            arr[row][col] = num;
	            System.out.print(arr[row][col]);
	            
	            num++;  
	         }
	         System.out.println();
	      }
	}
}
