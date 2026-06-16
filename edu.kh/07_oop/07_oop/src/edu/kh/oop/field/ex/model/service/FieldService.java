package edu.kh.oop.field.ex.model.service;

import edu.kh.oop.field.ex.model.vo.Korean;

public class FieldService {

//	static(정적,고정)
//	JVM static 메모리 영역(static영역,정적메모리영역,공유영역,메소드영역,클래스영역)
//	
//	필드세분화
//	
//	-멤버변수(인스턴스변수) 
//	  생성 : new 연산자를 통해  Heap 영역에 객체(인스턴스)가 생성 될 때 생성된다.
//	  소멸 : 객체가 어떠한 참조 변수에도 참조되지 않을 때 일정 시간 후 GC에 의해서 소멸된다. 
//	
//	-클래스변수(static 예약어가 붙은 변수)  
//	  생성 : 프로그램 실행 시 static 영역에 생성된다.
//	  소멸  : 프로그램 종료 시 소멸된다.
	public void example1() {
		Korean k1 = new Korean();
		Korean k2 = new Korean();
		Korean k3 = new Korean();
		
		k1.setName("홍길동");
		k1.setId("12313-156165");
		
		k2.setName("최광섭");
		k2.setId("15616-1561651");
		
		k3.setName("김세영");
		k3.setId("151656-15616");
		System.out.println(Korean.language);
//		k1.setNationalCode(1);
//		k2.setNationalCode(1);
//		k3.setNationalCode(1);
		
		Korean.nationalCode =1;//클래스변수(static변수)
		
		System.out.println(k1.toString());
		System.out.println(k2.toString());
		System.out.println(k3.toString());
		
	}

}
