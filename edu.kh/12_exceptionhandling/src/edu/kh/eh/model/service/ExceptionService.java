package edu.kh.eh.model.service;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.IIOException;
import javax.security.sasl.SaslException;

import edu.kh.eh.model.exception.SonjeolException;

public class ExceptionService {
	
//	예외(Exception) : 소스코드 수저으로 해결 가능한 오류(컴파일 에러, 런타임 에러)
//	try : 예외가 발생할 가능성이 있는 코드를 내부에 작성해서 시도한다.
//	catch :  try 구문에서 예외가 발생하면 잡아내서 처리한다.
//	finally : try-catch 수행 후 마지막에 무조건 실행해야 하는 코드를 작성한다.
//	java.lang.Exception : 모든 예외의 최상위 부모
//	Exception e로 모든 예외처리시 예외 상황별로 다른 처리를 할수 없게된다.
//	try에서 예외 발생 시 catch문이 작성 순서대로 실행되며 Exception도 객체로 상속되므로 최상위 부모인 Exception으로 catch문 작성시 나중에 작성한 catch문 실행이 안되므로 자식타입의 Exception 순으로 작성한다.
//	throws : 현재 메소드에서 발생한 예외를 호출부로 던져준다.
//	throw : 예외 강제 발생 구문
//	자식이 오버라이딩 시  예리를 처리할 수 있거나 재정의한 코드가 예외를 발생시키지 않으면 throws에 작성된 예외를 개수를 적게 또는 작성하지 않을 수 있다.
//	CheckedException : 반드시 예외처리 구문을 작성해야되는 Exception 소스코드 또는 if문으로 해결 불가능하기 때문에 예외 처리 구문을 강제로 작성해야 된다. 
//	UnCheckedException : 반드시 예외처리 구문을 작성하지 않아도 되는 Exception RuntimeException만 자식의 해당된다 개발자가 코드 작성중 또는 사용자가 프로그램 이용 중 실수로 인행 발생하는 예외(소스코드 수정또는 if문으로 해결)	
	public void example1() {
	Scanner sc = new Scanner(System.in);
	System.out.println("두 정수를 입력받아 나눈 몫을 출력 ");
	
	System.out.print("정수입력1 : ");
	int num1 = sc.nextInt();
	
	System.out.print("정수입력2 : ");
	int num2 = sc.nextInt();
	
		try {
			
		System.out.println(num1 + " / " + num2 + " = " + num1/num2 );
	//	       (발생한 예외 객체를 참조할 잠조 변수)
		}catch (ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
			
		}finally {
			System.out.println("계산 종료.");
		}
	
	}
	public void example2() {
//		3칸짜리 int 배열을 생성하여 입력받은 정수로 모든 배열 요소를 초기화하고 순서대로 출력
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[3];
		try {
			for(int i = 0; i<arr.length; i++) {
				System.out.print(i+"번째 인덱스 요소 : ");
				arr[i] = sc.nextInt();
			}
			for(int i = 0; i<=arr.length; i++) {
				System.out.println(arr[i]);
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("배열 범위를 초과해서 접근할 수 없습니다.");
		}catch(InputMismatchException e) {
			System.out.println("정수가 아닌 내용이 입력되었습니다.");
		}
		
	}
	public void example3() {
//		try-catch 중복사용
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[3];
		try {
			for(int i =0; i<arr.length; i++) {
				System.out.print(i + "번째 인덱스 입력 : ");
				try {
					
					arr[i] = sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("정수만 입력해주세요.");
					sc.nextLine();
					i--; //
				}
			
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("배열 범위 초과로 프로그램 종료.");
		}
		
	}
	public void example4() {
//		catch문에 작성되는 예외 클래스의 상속 관계
//		catch문 여러개 작성하는 경우 상속 관계를 고려해야 한다.
//		발생하는 예외들도 예외클래스를 이용해 생성된 예외 객체들이다.
//		catch(예외참조변수) 구문의 예외참조변수로 생성된 예외 객체를 참조한다.
//		예외에도 상속 관계가 있다 == 부모, 자식관계가 있다. == 다형성이 적용된다.
		String[] arr = new String[3];
		arr[0] = "사과";
		arr[1] = "바나나";
		try {
			for(int i=0; i<arr.length; i++) {
				System.out.println(arr[i].charAt(0));
			}
			
		}catch(NullPointerException e) {
			
		}catch(Exception e) {
			System.out.println("null은 참조할 수 없습니다.");
		}
	}
	public void example5() {
		Scanner sc = new Scanner(System.in);
		int sel = 0;
		do {
			try {
				System.out.println("[실행할 메소드 선택 (1,2)]");
				System.out.print("번호 입력 : ");
				sel = sc.nextInt();
				
				switch(sel) {
				case 1: methodA();break;
				case 2: methodB();break;
				case 0:System.out.println("프로그램 종료"); break;
				default : System.out.println("잘못 입력하셧습니다.");
				}
			}catch(InputMismatchException e) {
				System.out.println("정수 1,2만 입력해주세요.");
				sc.nextLine();
				sel = -1;
			}catch(ArithmeticException e) {
				System.out.println("0으로 나눌 수 없습니다.");
			}catch(Exception e ) {
				System.out.println("뭔지 모르겠지만 예외처리함");
				e.printStackTrace();
			}
		}while(sel != 0);
	
	}
	
	public void methodA() throws ArithmeticException {
		
		System.out.println(2/0);
	}
	
	public void methodB() throws IOException {
		throw new IOException();
	}
	
	public void example6() {
//		CheckedException : 반드시 예외처리 구문을 작성해야되는 Exception
//		UnCheckedException : 반드시 예외처리 구문을 작성하지 않아도 되는 Exception

		
//		throw new RuntimeException();
//		예외처리 구문이 강제되지 않아서 빨간줄이 안나타난다.
		
//		throw new IOException();
//		에러가 발생할 가능성이 있기 때문에 예외 처리 구문이 강제된다.
		
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 1 : ");
		int num1 = sc.nextInt();
		System.out.print("입력 2 : ");
		int num2 = sc.nextInt();
		if(num2 == 0) {
			System.out.println("0으로 나눌 수 없습니다.");
		}else {
			
			System.out.println(num1/num2);
		}
	}
	public void example7() {
//		사용자 정의 예외 : Java에서 제공하는 Exception Class만으로 처리할 수 없는 예외를 사용자가 필요에 의해서 만드는 Exception Class
		
		double money = 1000000.0;
		int count = 0;
//		System.out.println(ran);
		try {
			
			for(int i = 0; i<10; i++) {
				int ran = (int)Math.round(Math.random());
				
				int sign = 0;
				if(ran == 1) {
					sign = +1;
				}else {
					sign = -1;
					count += 1;
					if(count == 3) {
						throw new SonjeolException();
					}
				}
				int ran2 = (int)(Math.random()*20+1);
				
				money = money + (money*(sign*(ran2/100.0)));
				System.out.println(money);
			}
		}catch(SonjeolException e){
			e.printStackTrace();
		}
	}
	
}
