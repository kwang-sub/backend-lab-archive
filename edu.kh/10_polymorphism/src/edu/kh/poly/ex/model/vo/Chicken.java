package edu.kh.poly.ex.model.vo;

public class Chicken extends Animal{
	private int wing;
	
	public Chicken() {
	
	}
	
	
	
	

	public Chicken(String type, String eatType, String habitation, int wing) {
		super(type, eatType, habitation);
		this.wing = wing;
	}





	public int getWing() {
		return wing;
	}





	public void setWing(int wing) {
		this.wing = wing;
	}





	@Override
	public void move() {
		System.out.println("두발로 뛰어 다니면서 날개를 파닥거림");
		
	}

	@Override
	public void eat() {
		System.out.println("부리로 모이를 쪼아먹음");
		
	}






	@Override
	public void breath() {
		System.out.println("코와 입으로 빠르게 쉰다.");
		
	}
	
	
	@Override
	public String toString() {
		return super.toString()+"Chicken [wing=" + wing + "]";
	}
	
	
}
