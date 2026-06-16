package edu.kh.array2.practice.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class PracticeService {
	
	public void practice1() {
		String[][] arr = new String[3][3];
		for(int i =0; i<arr.length; i++) {
			for(int j =0; j<arr[i].length;j++) {
			arr[i][j]= "("+i+", "+j+")";
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	public void practice2() {
		int[][] arr = new int[4][4];
		int num = 1;
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				arr[i][j]=num;
				num++;
				System.out.printf("%2d ",arr[i][j]);
			}
			System.out.println();
		}
	}
	public void practice3() {
		int[][] arr = new int[4][4];
		int num = 16;
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				arr[i][j]=num;
				num--;
				System.out.printf("%2d ",arr[i][j]);
			}
			System.out.println();
		}
	}
	public void practice4() {
		int[][] arr = new int[4][4];
		for(int i=0; i<arr.length-1; i++) {
			for (int j=0; j<arr[0].length-1; j++) {
				arr[i][j] = (int)(Math.random()*10+1); 
				arr[3][j] += arr[i][j];
				arr[i][3] += arr[i][j];
				
				
			}
			
		}
	
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				System.out.printf("%2d ",arr[i][j]);
				if(i==arr.length-1||j==arr[0].length) {
					if(!(i==arr.length-1&&j==arr[0].length-1)) {
						arr[3][3]+=arr[i][j];
						
					}
				}
			}
			System.out.println();
		}
	}
	public void practice5() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("행 크기 : ");
			int num1 = sc.nextInt();
			System.out.print("열 크기 : ");
			int num2 = sc.nextInt();
			char[][] arr= new char[num1][num2];
			if(num1>0 && num1 <11 && num2>0 && num2<11){
				for(int i=0; i<arr.length; i++) {
					for(int j=0; j<arr[0].length;j++) {
						arr[i][j]=(char)((int)((Math.random()*26)+65));
					}
					
				}
				for(int i=0; i<arr.length; i++) {
					for(int j=0; j<arr[0].length; j++) {
						System.out.print(arr[i][j]+" ");
					}
					System.out.println();
				}
				break;
			}
			else {
				System.out.println("반드시 1~10사이의 정수를 입력해야 합니다.");
			}
		}
	}
	public void practice6() {
		Scanner sc = new Scanner(System.in);
		System.out.print("행의 크기 : ");
		int input = sc.nextInt();
		char[][] arr = new char[input][];
		char ch= 'a';
		for(int i=0; i<arr.length; i++) {
			System.out.print(i+"헹 열의 크기 : ");
			int in = sc.nextInt();
			arr[i]=new char[in];
			for(int j=0; j<arr[i].length;j++) {
				
			}
		}
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j]=ch++;
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void practice7() {
		String[] students = {"강건강","남나나","도대담","류라라","문미미","박보배","송성실","윤예의","진재주","차천축","피풍표","홍하하"};
		String[][] arr1 = new String[3][2];
		String[][] arr2 = new String[3][2];
		System.out.println("====1분단====");
		int n = 0;
		for(int i=0; i<arr1.length; i++) {
			for(int j=0; j<arr1[0].length; j++) {
				arr1[i][j]=students[n];
				n++;
				System.out.print(arr1[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("====2분단====");
		for(int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[0].length; j++) {
				arr2[i][j]=students[n];
				n++;
				System.out.print(arr2[i][j]+" ");
			}
			System.out.println();
		}
		
		
		
	}
	public void practice8() {
		String[] students = {"강건강","남나나","도대담","류라라","문미미","박보배","송성실","윤예의","진재주","차천축","피풍표","홍하하"};
		String[][] arr1 = new String[3][2];
		String[][] arr2 = new String[3][2];
		System.out.println("====1분단====");
		int n = 0;
		for(int i=0; i<arr1.length; i++) {
			for(int j=0; j<arr1[0].length; j++) {
				arr1[i][j]=students[n];
				n++;
				System.out.print(arr1[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("====2분단====");
		for(int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[0].length; j++) {
				arr2[i][j]=students[n];
				n++;
				System.out.print(arr2[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("==============");
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 학생 이름을 입력하세요 : ");
		String name = sc.next();
		for(int i=1; i<=arr1.length; i++) {
			for(int j=0; j<arr1[0].length; j++) {
			
				if(name.equals(arr1[i-1][j]) && j%2==0) {
					System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 왼쪽에 있습니다.\n",name,i);			
					}else if(arr1[i-1][j].equals(name)&& j%2==1) {
						System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 오른쪽에 있습니다.\n",name,i);
					}
				
			}	
		}
		for(int i=1; i<=arr2.length; i++) {
			for(int j=0; j<arr2[0].length; j++) {
			
				if(name.equals(arr2[i-1][j]) && j%2==0) {
					System.out.printf("검색하신 %s 학생은 2분단 %d번째 줄 왼쪽에 있습니다.\n",name,i);			
					}else if(arr2[i-1][j].equals(name)&& j%2==1) {
						System.out.printf("검색하신 %s 학생은 2분단 %d번째 줄 오른쪽에 있습니다.\n",name,i);
					}
				
			}	
		}
	}
	
}
