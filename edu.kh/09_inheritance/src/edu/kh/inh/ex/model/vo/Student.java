package edu.kh.inh.ex.model.vo;

public class Student extends Person {

	private int grade;
	private int classroom;
	
	public Student() {
		super();
//		super(); (super생성자,부모생성자) : 자식 객체 생성시 상속받은 부모 객체가 먼저 생성 되어야 되기 때문에 자식 생성자 가장 첫 줄에 작성됨.미작성 시 컴파일러가 자동으로 추가 해준다.
		
	}

	public Student(String name, int age, int grade, int classroom) {
//		super(name,age);
		super.setName(name);
		super.setAge(age);
//		this.name = name;
//		this.age = age;
		this.grade = grade;
		this.classroom = classroom;
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
	@Override
	public String toString() {
		return super.toString() + " / " + grade + " / " + classroom;
	}
	
	
	public void test() {
		System.out.println("시험보기");	
	}
	public void test(String subject) {
		System.out.println(subject + "과목 시험보기");
	}
	public String test(String sub1,String sub2) {
		return sub1+ " / " + sub2 + " 두 과목 시험보기";
	}
	protected int test (int num) {
		return num + 10;
	}
	public final void study() {
		System.out.println("학생은 공부해야 된다");
	}
	@Override
	public boolean equals(Object obj) {
		Student other = (Student)obj;
		if(!this.getName().equals(other.getName())) {
			return false;
		}else if( this.getAge() != other.getAge()) {
			return false;
		}else if(this.grade != other.grade) {
			return false;
		}else if(this.classroom != other.classroom) {
			return false;
		}else {
			return true;
			
		}
		
	}
}
