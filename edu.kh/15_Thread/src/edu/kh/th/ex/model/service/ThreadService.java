package edu.kh.th.ex.model.service;

import java.util.Scanner;

import edu.kh.th.ex.model.thread.ThreadEx1;
import edu.kh.th.ex.model.thread.ThreadEx2;
import edu.kh.th.ex.model.thread.ThreadEx3;
import edu.kh.th.ex.model.thread.ThreadEx4;

public class ThreadService {
	public void example1() {
		ThreadEx1 th1 = new ThreadEx1();
//		th1.run();
		th1.start();
		ThreadEx2 th2 = new ThreadEx2();
//		th2.run();
		Thread thread = new Thread(th2,"스레드2");
		thread.start();
		
	}
	public void example2() {
//		스레드 우선 순위 확인
		for(int i=1; i<=20; i++) {
			ThreadEx3 th3 = new ThreadEx3();
			Thread t = new Thread(th3,"스레드" + i);
			if(i==10) {//10번째 스레드가 최우선으로 설정
				t.setPriority(Thread.MAX_PRIORITY);
			}else {
				t.setPriority(Thread.MIN_PRIORITY);
			}
			t.start();
		}
	}
	public void example3() {
		Thread t1 = new Thread(new ThreadEx4(3),"1번");
		Thread t2 = new Thread(new ThreadEx4(7),"2번");
		Thread t3 = new Thread(new ThreadEx4(20),"3번");
		System.out.println("시작");
//		t1.start();
//		t2.start();
		t3.start();
		Scanner sc = new Scanner(System.in);
		System.out.println("아무 값이나 입력하세요 : ");
		String input = sc.next();
		
		t3.interrupt();//아무 값이나 입력 받으면 t3스레드 동작을 멈춘다.
		
		System.out.println(t1.isInterrupted());
		System.out.println(t2.isInterrupted());
		System.out.println(t3.isInterrupted());
		
	}
}
