package edu.kh.variable.ex.service;

public class C_Casting {
//	형변환
//	컴퓨터에서의 값 처리 원칙
//	1.같은 종류의 자료형만 대입 가능
//	2.같은 종류 자료형만 계산가능
//	3.계산의 결과도 같은 종류의 자료형이 나와야 함
//	->원칙이 지켜지지 않을 때 형변환이 필요(자동형변환,강제형변환)
	public void example1() {
//		자동형변환:값의 범위가 작은 자료형과 큰 자료형 간의 연산시 컴파일러가 자동으로
//		범위가 작은 자료형을 큰 자료형과 같은 형태로 변환시키는것.
		
		int iNum = 12;
		double dNum = 3.3;
		System.out.println(iNum+dNum);
		double result = iNum + dNum;
		System.out.println("result: " + result);
		
		int iNum2 = 2147483647;
		long lNum2 = 1000000000L;
	    long result2 = iNum2 + lNum2;
		System.out.println(result2);
		
		float fNum3 = 1.2f;
		double dNum3 = fNum3;
//		long -> float
//		float의 값의 범위가 long보다 크기때문에 자동형변환가능
		long lNum4 = Long.MAX_VALUE;
		float fNum4 = lNum4;
		
		System.out.println("lNum4: " + lNum4);
		System.out.println("fNum4: " + fNum4);
		
		char ch5 = 'A';
		int iNum5 = ch5;
		System.out.println(iNum5);
		
		char ch6 =65;
//		에러가 나지 않는 이유:
//		char 자료형은 원래 0~65535 사이의 정수를 저장하는 변수
//		그래서 char 자료형에 숫자를 대입하는게 문제가 되지 않는다.
//		그런데 정수를 나타낼 수 있는 표기법이 int,long 두 개만 존재함.
//		따라서 별도의 char 정수 표기법을 만들지 않고 대입시
//		작성되는 int 표기법을 char 자료형 정수로 인식하게 java에서 정해둠
		
		System.out.println("ch6: " + ch6);
		
	}
			 
	public void example2() {
//		강제형변환:값의 범위가 큰 자료형을 값의 범위가 작은 자료형으로 변환
//		->큰 데이터를 잘라서 넣어야 하리 때문에 자동으로 진행되지 못하고 강제로 수행 시켜야함.
		int iNum = 290;
		byte bNum = (byte)iNum;
		System.out.println(iNum);
		System.out.println(bNum);
	
//		실수형->정수형 강제형변환
		int iNum2 = 10;
		double dNum2 = 1.25;
		System.out.println(iNum2*dNum2);
		int result2 = (int)(iNum2*dNum2);	
		System.out.println(result2);
		System.out.println(iNum2*(int)dNum2);
		int iNum3 = 65;
		
		char ch3 = 65;//char에 값 대입시 int 리터럴 표기법을 사용할 수 있다.
		char ch4 = (char)iNum3;
		System.out.println("ch3:"+ch3);
		System.out.println("ch4:"+ch4);
	}
	
	public void example3() {
		
		int views = 2147483647;
		System.out.println("views: " + views);
		
		int views2 = views + 1;
		System.out.println("views2: " + views2);
//		데이터 오버플로우는 코드상 문제없음 ->서비스중 문제가 발생하지 않도록 기획,설계단계에서 예측을 잘해야함.
	}
	public void example4() {
		String name = "홍길동";
		int age = 20;
		double height = 180.45;
//		홍길동님은 20세이며, 키는 180.45cm 입니다
		
		System.out.println(name+"님은 "+age+"세이며, 키는 "+height+"cm 입니다.");
		System.out.printf("%s님은 %d세이며, 키는 %.2fcm 입니다.", name, age, height);
	}
	
	
}
