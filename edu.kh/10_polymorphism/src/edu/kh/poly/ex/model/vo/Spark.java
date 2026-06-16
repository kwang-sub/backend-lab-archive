package edu.kh.poly.ex.model.vo;

public class Spark extends Car{
	
	private String color;
	public Spark() {
	
	}
	
	public Spark(String engine, int wheel, String handle, String color) {
		super(engine, wheel, handle);
		this.color = color;
	}

	public Spark(String color) {
		super();
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String toString() {
		return super.toString() + " / " + color;
	}
}
