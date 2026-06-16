package edu.kh.oop.encapsulation.model.vo;

public class Account {
//	속성(필드) : 변수 작성 부분 

//	멤버변수 : 해당 클래스에 소속된 변수
//	클래스변수
	private String name;
	private String accountNumber;
	private String password;
	private int bankCode;
	private int money;

//  기능(메소드)	
	public void depsit() {
		
	}
	public void withdraw() {
		
	}
//	private으로 직접 접근이 제한된 멤버 변수에 간접적으로 접근할 수 있는 기능을 작성
//	멤버변수 하나당 두개의 기능이 생성됨
//	getter/setter


//	setter 작성 방법
//	public void set멤버변수명(멤버변수자료형 멤버변수명){
//	this.멤버변수명=멤버변수명;
//  }
	public void setName(String name) {
		this.name=name;//전달 받은 매개변수 name 값을 멤버 변수 name에 저장
//		this.:현재클래스
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public void setMoney(int money) {
		this.money = money;
	}
//	getter 작성 방법
//	public 멤버변수자료형 get멤버변수명(){
//	return 멤버변수명;
//	}
	
//	return : 메소드를 종료하고 오른쪽에 작성된 값을 호출부로 돌려보냄
	public String getName() {
//		   String -> return 하는 값의 자료형 ==반환형(return type)
		return name;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getPassword() {
		return password;
	}
	public int getBankCode() {
		return bankCode;
	}
	public int getMoney() {
		return money;
	}
}
