package edu.kh.inh.ex.model.vo;

public final class HighSchoolStudent extends Student {

	private String line;
	
	public HighSchoolStudent() {
		
	}
	

	public HighSchoolStudent(String name, int age, int grade, int classroom, String line) {
		super(name, age, grade, classroom);
		this.line = line;
	}
	
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return super.toString() + " / " + line;
	}
//	@Override
//	public void study() {
//		System.out.println("관심있는 분야 공부하자");
//	}
}
