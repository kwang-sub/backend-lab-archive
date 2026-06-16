package edu.kh.array.ex.service;

import java.util.Arrays;
import java.util.Collections;

//		얕은 복사 : 참조하는 배열의 주소만을 복사
//		-> 객체의 매개변수, 반환형 전달 시 사용
//		깊은 복사 : 원본 배열과 동일한 자료형, 동일한 크기를 가지는 배열을 만들어 내부 데이터까지 똑같이 작성하는 복사
//		->원본 데이터 유지, 원본과 똑같은 새로운 배열 필요 시 사용
//		Arrays 클래스 : java.util 패키지에 존재 하는 배열을 쉽게 사용하거나 추가적인 기능을 제공해주는 클래스
//		Arrays.toString(배열명) : 해당 배열에 있는 모든 값을 하나의 문자열로 만들어준다
//		hashCode() : 주소를 이용해서 정해진 길이의 정수를 만들어 내는 기능
public class ArrayService2 {
	public void example1() {
		int[] arr = {10,20,30,40,50};
		int[] copyarr = arr;
		
		System.out.println("arr : "+Arrays.toString(arr));
		System.out.println("copyarr : "+Arrays.toString(copyarr));
		copyarr[0] = 99;
		System.out.println("arr : "+Arrays.toString(arr));
		System.out.println("copyarr : "+Arrays.toString(copyarr));
		System.out.println(arr.hashCode());
		System.out.println(copyarr.hashCode());
	}
	public void example2() {
		int[] arr = {10,20,30,40,50};
		int[] copyArr = new int[arr.length];
//		for(int i = 0; i<arr.length; i++) {
//			copyArr[i] = arr[i];
//		}
//		System.arraycopy
		System.arraycopy(arr,0,copyArr,0,arr.length);
		
		System.out.println("arr : "+ Arrays.toString(arr));
		System.out.println("copyArr : "+ Arrays.toString(copyArr));
	
		copyArr[0] = 99;
		System.out.println("arr : "+ Arrays.toString(arr));
		System.out.println("copyArr : "+ Arrays.toString(copyArr));
		
	}
	public void example3() {
		int[] arr = {1,8,7,5,6,4,3,2,9,10};
//		삽입,선택,버블정렬 알고리즘
//		 for(int i=1 ; i<arr.length ; i++) {
//	         
//	         for(int j=0 ; j < i ; j++) {
//	            
//	            if(arr[i] < arr[j]) {
//	               
//	               int tmp = arr[i];
//	               arr[i] = arr[j];
//	               arr[j] = tmp;
//	            }
//	         }
//	         
//	      }
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		Integer[] arr2 = {1,8,7,5,6,4,3,2,9,10};
		Arrays.sort(arr2,Collections.reverseOrder());
		System.out.println(Arrays.toString(arr2));
	}
	public void example4() {
		int[] arr = {1,2,3,4,2};
		for(int i =0; i<arr.length-1; i++) {
			for(int j =i+1; j<arr.length; j++) {
				if(arr[i]==arr[j]) {
					System.out.println(arr[i]+"는 중복입니다");
				}
			}
		}
	}
	public void example5() {
		int[] arr = new int[6];
		for(int i = 0; i<arr.length; i++) {
			arr[i]=(int)(Math.random()*45+1);
			for(int j =0; j<i; j++) {
				if(arr[i]==arr[j]) {
					i--;
					break;
				}
			}
			
		}
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
