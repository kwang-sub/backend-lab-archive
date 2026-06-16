package edu.kh.th.ex.model.thread;

public class ThreadEx4 implements Runnable{
	private int count;
	
	public ThreadEx4(int count) {
		super();
		this.count = count;
	}

	@Override
	public void run() {
		try {
			for(int i=1; i<=count; i++) {
				Thread.sleep(1000);
//				sleep()으로 스레드가 일시정지 되어있는 동안 interrupt() 메소드에 의해 정지하라는 기능이 동작하면 sleep()이 InterruptedException을 발생시킨다.
				System.out.println(Thread.currentThread().getName() + " : " + i + "초");
			}
			System.out.println(Thread.currentThread().getName() + " 스레드 종료");
		}catch(InterruptedException e) {
//			e.printStackTrace();
			Thread.currentThread().interrupt();
			System.out.println("@@"+Thread.currentThread().isInterrupted());
		}
		
	}

}
