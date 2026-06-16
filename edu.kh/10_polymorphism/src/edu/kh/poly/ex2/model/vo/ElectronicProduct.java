package edu.kh.poly.ex2.model.vo;

public interface ElectronicProduct {
	
	 public static final int KOREA_VOLT = 220;
	 static final int JAPAN_VOLT = 110;
//	 public을 작성하지 않아도 public으로 인식됨

	 int USA_VOLT1 = 115;
	 static int USA_VOLT2 = 230;
	 
	 
	 
	 public abstract void powerOn();
	 
	 void powerOff();
	 
//	 부모                자식
//	인터페이스 - 인터페이스
	 
	 
}
