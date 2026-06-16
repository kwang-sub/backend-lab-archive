package edu.kh.oop.method.ex.model.vo;

public class Product {
	
	private String pName;
	private int Price;
	private String serialNumber;
	
	
	public Product() {
		
	}
	
	public Product(String pName, int Price, String serialNumber) {
		this.pName = pName;
		this.Price = Price;
		this.serialNumber = serialNumber;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	

}
