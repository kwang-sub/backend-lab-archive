package edu.kh.poly.ex2.model.vo;

public interface Printer extends ElectronicProduct {
	public abstract  void print();
//	인터페이스의 모든 메소드는 묵시적으로 public abstract이다.
//	오버라이딩을 강제하는 abstract와 오버라이딩을 막는 final은 동시에 사용할수 없다.
	
}
