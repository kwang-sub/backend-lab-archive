package edu.kh.oop.constructor.ex.model.service;

import edu.kh.oop.constructor.ex.model.vo.User;

public class constructorService {
	public void example1() {
		User u1 = new User();
		u1.setUserId("user01");
		u1.setUserPwd("pass01");
		u1.setUserName("유저1");
		u1.setAge(20);
		u1.setGender('여');
	
		User u2 = new User();
		u2.setUserId("user02");
		u2.setUserPwd("pass02");
		u2.setUserName("유저2");
		u2.setAge(30);
		u2.setGender('남');
		
		User u3 = new User("user03", "pass03", "유저삼", 25,'여') ;
		
		User u4 = new User("kwang", "kwang1");
		
		System.out.println("u1 : " + u1.toString());
		System.out.println("u2 : " + u2.toString());
		System.out.println("u3 : " + u3.toString());
		System.out.println("u4 : " + u4.toString());
//		user 객체를 하나 만들어 참조변수 u2에 저장하고 각 필드 값을 초기화(user02.pass02,유저2,30,남)
	}
}
