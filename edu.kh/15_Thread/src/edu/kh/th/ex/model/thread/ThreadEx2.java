package edu.kh.th.ex.model.thread;

public class ThreadEx2 implements Runnable{

	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("X");
			if(i%20 ==0) {
				System.out.println();
			}
		}
		
		System.out.println( Thread.currentThread().getName()+ "출력 완료");
		
	}
		
	
	
}
