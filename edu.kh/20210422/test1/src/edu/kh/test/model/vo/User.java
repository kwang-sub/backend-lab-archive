package edu.kh.test.model.vo;
//필드 : userId, userPwd, name
//
//기본생성자, 매개변수 있는 생성자, getter/setter
public class User {
	private String userId;
	private String userPwd;
	private String name;
	
	public User() {
		
	}
	
	public User(String userId, String userPwd, String name) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	 "아이디 : OOO, 비밀번호 : OOO, 이름 : OOO" 
	@Override
	public String toString() {
		return "아이디 : " +  userId + ", 비밀번호 : " + userPwd + ",이름 : " + name;
	}
	
	
}
