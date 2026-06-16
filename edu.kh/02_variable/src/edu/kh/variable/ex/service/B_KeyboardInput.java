package edu.kh.variable.ex.service;

import java.util.Scanner;

public class B_KeyboardInput {

	public  void inputExample1() {
		Scanner sc = new Scanner(System.in);
		//이름을 입력받아 저장할 변수 선언
		String inputName;
//		콘솔창에"이름을 입력해주세요: " 문자열 출력
		System.out.print("이름을 입력해주세요:");
//		Scanner를 이용하여 이름을 입력 받아 inputName에 대입
		inputName = sc.next();// 사용자가 입력한 다음 단어를 읽어옴
		System.out.println(inputName+"님 환영합니다");
//		키보드로 정수,실수,문자열을 입력받는 기능을 제공하는 클래스
		
	}
//	여러번 입력받기
	public void inputExample2() {
//		이름 나이 키를 입력받아 변수에 저장
//		" ooo님은  oo세 이며, 키는ooo.ooocm 입니다."
//		1.입력을 위해 scanner객체생성
		Scanner sc = new Scanner(System.in);
//		2."이름 입력:"구문출력후 변수에대입
		System.out.print("이름입력: ");
		String inputName = sc.next();
//		"나이입력:"출력후 변수대입
		System.out.print("나이입력: ");
		int inputAge = sc.nextInt();
		System.out.print("키입력: ");
		double inputHight = sc.nextDouble();
		System.out.println(inputName+"님은 "+inputAge+"세 이며, "
				+ "키는"+inputHight+"cm 입니다.");
	}

}
