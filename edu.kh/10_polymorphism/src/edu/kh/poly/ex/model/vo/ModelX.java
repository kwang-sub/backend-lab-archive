package edu.kh.poly.ex.model.vo;

public class ModelX extends Car{
	private String battery;
	public ModelX() {
	
	}
	public ModelX(String engine, int wheel, String handle, String battery) {
		super(engine, wheel, handle);
		this.battery = battery;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String toString() {
		return super.toString() + " / " + battery;
	}
}
