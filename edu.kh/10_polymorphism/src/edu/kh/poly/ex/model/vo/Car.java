package edu.kh.poly.ex.model.vo;

public class Car {
	private String engine;
	private int wheel;
	private String handle;
	
	public Car() {
		
	}

	public Car(String engine, int wheel, String handle) {
		super();
		this.engine = engine;
		this.wheel = wheel;
		this.handle = handle;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}
	
	@Override
	public String toString() {
		return engine + " / " + wheel + " / " + handle; 
	}
	
}
