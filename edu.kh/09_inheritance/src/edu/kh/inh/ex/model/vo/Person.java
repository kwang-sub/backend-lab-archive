package edu.kh.inh.ex.model.vo;
//	오버라이딩(Overriding) : 자식 클래스가 상속 받은 부모 메소드를 재작성(재정의) 하는것
//	특징
//	-@Override 메소드 위에 어노테이션을 작성해야된다
// 	-오버라이딩 시 접근제한자는 부모 것과 같거나 더 넓은 범위를 가짌 수 있다.
// 	-오버라이딩 시 예외처리 클래스는 부모 것과 같거나 더 좁은 범위로 수정할 수 있다.
//	성립조건
//	-부모 클래스의 메소드와 메소드명이 동일해야된다
// 	-매개변수의 개수, 타입, 순서가 동일해야된다
// 	-리턴 타입이 동일 해야된다
//	-부모의 private 메소드는 오버라이딩 불가능 하다.
//
//	equals():상송 받은 클래스로 생성한 객체간의 동등 비교(값이 같은가)를 위해 자식 클래스에서 재정의 해서 사용하는 용도의 메소드

public class Person extends Object{
	
	private String name;
	private int age;
	
	public Person() {
		super();
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	Object의 toString() 오버라이딩
//	toString() : 객체가 가지고 있는 필드를 하나의 문자열로 나타낼 수 있도록 클래스 작성 시 재정의해서 사용하는 것을 목적으로 만들어진 메소드.
//	@Override 다음에 선언된 메소드는 재정의 되었음을 컴파일러 + 개발자에게 알려주는 역활

	@Override
	public String toString() {
		return name + " / " + age;
	}
	
	
	
}
