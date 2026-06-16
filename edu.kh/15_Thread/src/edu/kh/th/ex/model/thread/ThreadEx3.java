package edu.kh.th.ex.model.thread;

public class ThreadEx3  implements Runnable{

	@Override
	public void run() {
		
		int sum = 0;
		for(int i=0; i<100000000; i++) {
			sum += i;
			int sum2 = 0;
			for(int j=0; j<100000000; j++) {
				sum2 += j;
			}
		}
//		스레드 이름 출력
		System.out.println(Thread.currentThread().getName());
	}
	
}
