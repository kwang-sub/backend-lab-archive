package edu.kh.poly.ex2.model.vo;

public abstract class AllinoneLaserPrint extends AllinonePrinter {
//	(부)추상클래스 - (자)추상 클래스 상속시 extends 사용하고 자식이 부모의 추상 메소드를 물려 받지만 자식도 추상클래스이기 때문에 추상 메소드를 보유할 수 있어 오버라이딩 강제x
//	(부)추상클래스 - (자) 클래스 상속시  부모의 추상 메소드를 자식이 강제적으로 오버라이딩한다
	
	
	public abstract void laserPrint();
}
