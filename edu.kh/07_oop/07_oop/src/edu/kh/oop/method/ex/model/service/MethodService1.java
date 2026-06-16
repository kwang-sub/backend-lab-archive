package edu.kh.oop.method.ex.model.service;

import edu.kh.oop.method.ex.model.vo.Product;

public class MethodService1 {
//	매개변수x 반환형x 메소드
	public void example1() {
		System.out.println("매개변수,반환형 모두 없는 메소드");
 }
//	매개변수o 반환형x 메소드
	public void sum(int num1, int num2, int num3) {
	 	System.out.printf("%d + %d + %d = %d\n",num1,num2,num3,num1+num2+num3);
	}
	
	public void doublePlusMinus(double num1, double num2, double num3)  {
		System.out.printf("%f + %f - %f = %f\n",num1, num2, num3, num1+num2-num3);	
	}
	
//	매개변수o(배열) 반환형x 메소드
	public void arrSum(int[] arr) {
		int sum = 0;
		for(int i = 0; i<arr.length; i++) {
			sum += arr[i];
		}
		System.out.println("합계 : " + sum);
	}
	
//	매개변수o(클래스) 반환형o(Stirng) 메소드
	public String productString(Product prd) {
		String result = prd.getpName() + "/" + prd.getPrice() + "/" + prd.getSerialNumber();
		return result;
//		return 현재메소드를 종료하고 대입된 값을 반환한다.
		
	}
	
}
