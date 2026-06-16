package edu.kh.oop.method.ex.model.service;

import edu.kh.oop.method.ex.model.vo.Product;

public class MethodService2 {
	
//	methodservice1에 있는 기능을 한번에 호출하기
	public void start() {
		MethodService1 service = new MethodService1();
		service.example1();
		service.sum(5, 7, 6);
		service.doublePlusMinus(9.8, 8.1, 5.2);
		int[] numbers = {10,20,30,55};
		service.arrSum(numbers);
		
		
		Product prd1 = new Product("광섭모니터", 10000000, "123123132");
		Product prd2 = new Product("세영모니터", 10000000, "123123132");
		Product prd3 = new Product("KH모니터", 10000000, "123123132");
		
//		반환된 String 바로 출력
		System.out.println(service.productString(prd1));
		
//		반환된
		String result2 = service.productString(prd2);
		System.out.println(result2);
		

	}
}
