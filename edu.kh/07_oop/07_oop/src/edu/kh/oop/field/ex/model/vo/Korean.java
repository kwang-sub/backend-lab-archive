package edu.kh.oop.field.ex.model.vo;

public class Korean {
	
	public static final String language = "한글";
	private String id;
	private String name;
//	private int nationalCode = 82;
	public static int nationalCode = 82;
	
	public void quickly() {
		System.out.println("빨리!빨리!");
	}
	
	
	public void setNationalCode(int nationalCode) {
		this.nationalCode = nationalCode;
	}
//	public void setLanguage(String language) {
//		this.language = language;
//	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getNationalCode() {
		return nationalCode;
	}
//	public String getLanguage(){
//		return language;
//	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
//	필드값을 하나의 문자열로 반환하는 메소드
	public String toString() {
		String result = name + "/" + id + "/" + language + "/" + nationalCode;
		return result;
	}
	
}
