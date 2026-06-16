package edu.kh.th.ex.model.thread;
//	별도 스레드 생성을 위한 클래스 : 다른 스레드와 동시에 코드가 수행된다.
//	스레드 생성 방법
//	1. Thread 클래스 상속 : Thread 상속 시 상속 받은 메소드 중 run() 메소드를 오버라이딩 해야된다. run() 메소드는 스레드가 생성 되서 별도로 작업하고자 하는 코드를 작성하는 메소드
//	Thread.setName("스레드명") : 스레드 이름지정        
//	Thread를 상속 받은 객체를 별도의 스레드에서 실행하기 위해서는 변수명.start(); 메소드를 호출해야 한다.
//	->start() 호출 시 별도 스레드가 생성되며 run()에 작성된 내용이 실행된다.
//
//	2. Runable 인터페이스 상속 (많이 사용하는 방법) : 인터페이스 상속은 오버라이딩을 강제로 해야되며 인터페이스 사용 시 코드 일관성 증가한다.
//	Thread.currentThread() : 현재 실행중인 스레드를 반환
//	Runnable 인터페이스를 구현한 객체는 Thread 클래스 객체를 생성할 때 생성자 매개변수로 Runnable 인터페이스를 구현한 객체를 추가하여 생성한다.
//
//	Thread.sleep(지연시간(ms)) : 지정된 시간동안 스레드를 일시 정지 시키는 메소드
//	-static으로 선언 되어 있어서 Thread.sleep(지연시간(ms))모양으로 호출
//	-sleep()은 인터럽트 동작(다른 행위를 무시하고 최우선 동작) 중 하나로 InterruptedExecption을 발생할 가능성이 있다.->예외 처리 필요
//	boolean isInterrupted() : 현재 스레드가 interrupt() 메소드에 의해서 멈췄는지 확인하는 메소드

public class ThreadEx1 extends Thread {

	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("O");
			if(i%20 ==0) {
				System.out.println();
			}
		}
		setName("스레드1번");
		System.out.println(getName() + "출력 완료");
		
	}
	
}
