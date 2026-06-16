package edu.kh.oop.encapsulation.model.service;

import edu.kh.oop.encapsulation.model.vo.Account;

public class EncapulationService {

	public void example1() {
		Account ac = new Account();
//		캡슐화 원칙으로 인해 private로 멤버변수가 선언되면 직접접근을 할 수없다.
//		ac.name = "최광섭";
//		ac.accountNumber="010-8955-7994";
//		ac.password="123";
//		ac.bankCode=1;
//		ac.money=1000000;
//		setter를 이용한 간접접근
		ac.setName("최광섭");
		ac.setAccountNumber("010-8955-7994");
		ac.setPassword("123");
		ac.setBankCode(1);
		ac.setMoney(100000);
		
//		System.out.println(ac.money);
//		System.out.println(ac.password);
//		멤버 변수의 직접 접근이 가능하여 정보가 쉽게 유출됨.
		
//		캡슐화가 적용되어 있을 때 간접 접근으로 얻어오는 방법
		System.out.println(ac.getName());
		System.out.println(ac.getAccountNumber());
		System.out.println(ac.getPassword());
		System.out.println(ac.getBankCode());
		System.out.println(ac.getMoney());
		
		String name = ac.getName();
		System.out.println("name : " +name);
	}
	
}
