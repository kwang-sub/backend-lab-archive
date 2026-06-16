package edu.kh.oop.field.ex;

public class FieldTest1 {
//	접근제한자
	public int temp1=10;
	protected int temp2=20;
	int temp3=30;
	private int temp4=40;
	
	public void ex1() {
		System.out.println(temp1);
		System.out.println(temp2);
		System.out.println(temp3);
		System.out.println(temp4);
				
	}
	
//	temp4 getter 작성
	public int getTemp4() {
//		--> 반환형
		return temp4;
	}
}
