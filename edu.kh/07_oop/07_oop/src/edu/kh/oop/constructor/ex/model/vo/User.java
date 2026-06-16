package edu.kh.oop.constructor.ex.model.vo;

public class User {
	
//	클래스 구조
//	public class 클래스명{
//	 필드
//	 생성자
//	 메소드
//  }
	
	private String userId;
	private String userPwd;
	private String userName;
	private int age;
	private char gender;
	
	
//	생성자
//	new 연산자를 통해 객체 생성시 생성된 객체에 값을 초기화 하고 지정된 기능을 수행하는 것이 목적이다.
//
//	생성자 작성시 주의사항
//	1.생성자명은 반드시 클래스명과 동일해야한다.
//	2.반환형이 존재하지 않는다.
//	3.매개변수 있는 생성자 작성시 기본 생성자가 컴파일러에 의해 자동으로 생성되지 않으므로 기본생성자를 반드시 작성해야한다.
//	
//	기본생성자
//	[접근제한자] 클래스명(){}
//	기본생성자가 작성되지 않은 경우 컴파일러에 의해서 자동으로 기본 생성자가 생성된다.
//	
//	매개변수 있는 생성자
//	매개변수 : 메소드 호출 시 전달되는 값을 저장할 변수(매개체 역활)
//	객체 생성 시 매개변수로 전달 받은 값을 이용해서 필드 값을 초기화 하는  생성자
//	매개변수로 참조 자료형이 전달되는 과정에서는 얕은 복사가 이루어 진다.
	
//	오버로딩
//	한 클래스 내에 동일한 이름의 메소드를 여러 개 작성한는 방법
//	예로는 매개변수있는 생성자 생성시 기본생성자 생성하고 같은 이름의 매개변수있는 생성자를 생성하는걸 생각하면 된다.
//	
//	오버로딩 성립 조건
//	1.메소드명이 같아야 한다.
//	2.매개변수의 타입,개수,자료형의 순서 중 하나라도 달라야 한다.
//	
//	오버로딩시 주의사항
//	매개변수의 순서를 바꾸게 될 경우 매개변수의 자료형을 확인해야한다.
//	
//	this() 생성자
//	같은 클래스 내에 있는 다른 생성자를 호출할 때 사용
//	생성자 내부에서만 작성할수 있고 반드시 첫번째 줄에 작성 되어야 한다.
//	오버로딩시에 다른 생성자에서 사용된 매개변수를 사용하는 방법
//
//	this() 생성자 사용이유
//	반복되는 생성자 구문의 소스코드 중복을 해결하기 위해서이다.
	public User() {
	}
	public User(String userId,String userPwd,String userName,int age,char gender) {
		
		this(userId,userPwd);
	
//		this.userId = userId;
//		this.userPwd = userPwd;
		
		this.userName = userName;
		this.age = age;
		this.gender = gender;
	}
	public User(String userId,String userPwd) {
		this.userId = userId;
		this.userPwd = userPwd;
	}
	public User(String userName,int age) {
		this.userName = userName;
		this.age = age;
	}
//	public User(String userId,String userName) {
//		this.userId = userId;
//		this.userName = userName;
//	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public String toString() {
		String result = userId + "/" + userPwd + "/" + userName + "/" + age + "/" + gender;
		return result;
	}
	

}
