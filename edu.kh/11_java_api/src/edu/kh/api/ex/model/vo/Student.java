package edu.kh.api.ex.model.vo;

public class Student {
	private String name;
	private int grade;
	private int classroom;
	
	
	public Student() {
		
	}


	public Student(String name, int grade, int classroom) {
		super();
		this.name = name;
		this.grade = grade;
		this.classroom = classroom;
	}
	
	public String getter() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public int getClassroom() {
		return classroom;
	}


	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
//	Object.toString()
//	java.lang 패키지에 존재
//	객체가 가지고 있는 필드 값을 하나의 문자열로 반환할 수 있도록 하는 용도로 자바에서 기본 제공하는 메소드(오버라이딩 해서 사용)


	@Override
	public String toString() {
		return name + " / " + grade + " / " + classroom;
	}
	
//	Objext.equals(Object obj)
//	현재 객체와 전달 받은 객체가 동등한지 확인하는 메소드
//	
//	Object obj : 비교할려는 다른 객체 참조 변수
//	this.(ths 참조변수) : 현재객체의 시작주소를 저장하는 참조 변수 객체내에 생성되어 있음
//	this() (this 생성자) : 클래스에 작성된 다른 생성자를 호출하는 구문 클래스의 생성자 첫번째 줄에 작성되어 있음
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj == this) {
			return true;
		}
//		obj가 참조하는 객체가 Student 타입이 아닐때
		if(!(obj instanceof Student)) {
			return false;
		}
//	위에 세 경우가 아니면 서도 다른 객체
		Student other = (Student)obj;
		if(!name.contentEquals(other.name)) {
			return false;
		}else if (grade != other.grade) {
			return false;
		}else if (classroom != other.classroom) {
			return false;
		}
		return true;
		
	}
//	object.hashcode() : 동등한 객체 (equals() 결과과 true인 두객체)는 동일한 hashCode를 반환해야 한다.이러한 이유로 equals() 오버라이딩시 hashCode()도 오버라이딩함.
//						해시테이블에 정렬될 수 있는 값을 생성해서 검색 속도를 향상 시킴 ex) Hashset, LinkedHashSet, HashMap
	@Override					
	public int hashCode() {
		final int PRIME = 31; //prime : 소수
		int result = 1;
		
		result = PRIME * result + (name == null ? 0 : name.hashCode());
		result =  PRIME * result + grade;
		result = PRIME * result + classroom;
		
		return result;
	}
	
}
