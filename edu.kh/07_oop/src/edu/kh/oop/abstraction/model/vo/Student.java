package edu.kh.oop.abstraction.model.vo;
//	mode : 비즈니스 로직 관련 클래스를 모아두는 패키지
//	vo(value object) : 값 저장용 객체		
public class Student {

	public String name;
	public String schoolName;
	public int grade ;
	public char gender;
	
	public void study() {
		System.out.println(name + "은 공부를 열심히 합니다");
	}
	public void goToSchool() {
		System.out.println("등교하기");
	}
	public void goToHome() {
		System.out.println("집에 가기");
	}
	public void introduce() {
		System.out.printf("이름은 %s이고, %s %d학년 재학중인 %c자입니다.\n",name,schoolName,grade,gender);
	}
//	public void playGame() {
//		System.out.println("게임하기");
//	}  
//	학생에게 공통적으로 필요한 기능이 아니다.
}
