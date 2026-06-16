package edu.kh.basic.run;

import edu.kh.basic.service.BasicService;

public class BasicRun {
//	패키지:관련된 클래스를 모아두는 폴더
//	run 패키지:실행 관련된 클래스 모아두는 폴더
//	run 클래스:실행 관련 소스코드를 모아두는 파일
//	main 메소드:자바 프로그램을 실행하기 위한 메소드
//	method:기능
	public static void main(String[] args) {
		
//		System.out.println("BacicRun 실행");
//		다른 클래스에 있는 메소드를 실행 하는 방법
		BasicService service = new BasicService();
//		[호출할 메소드가 있는 클래스명] [변수명] = new [호출할 메소드가 있는 클래스명]
		service.printMessage();
		service.printTotal();
		
	}
}
