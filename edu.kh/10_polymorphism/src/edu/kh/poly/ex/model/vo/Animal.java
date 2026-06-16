package edu.kh.poly.ex.model.vo;

public abstract  class Animal {
//	추상클래스(abstract class) 
//	-추상 메소드를 포함한 클래스 :미완성된 내용을 담고 있는 클래스이므로 상속받은 자식이 반드시 미완성된 부분을 오버라이딩 해야된다
//	-추상 메소드를 포함하지 않아도 객체로 생성되면 안되는 클래스 
	
	private String type;
	private String eatType;
	private String habitation;
	
//	추상클래스의 생성자

	public Animal() {
	}
	
	public Animal(String type, String eatType, String habitation) {
		super();
		this.type = type;
		this.eatType = eatType;
		this.habitation = habitation;
	}


	


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEatType() {
		return eatType;
	}
	public void setEatType(String eatType) {
		this.eatType = eatType;
	}
	public String getHabitation() {
		return habitation;
	}
	public void setHabitation(String habitation) {
		this.habitation = habitation;
	}
	public abstract void move();
	public abstract void eat();
	public abstract void breath();

	@Override
	public String toString() {
		return "Animal [type=" + type + ", eatType=" + eatType + ", habitation=" + habitation + "]";
	}
	
	
	
	
}
