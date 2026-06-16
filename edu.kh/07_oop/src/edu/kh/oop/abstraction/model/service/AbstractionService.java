package edu.kh.oop.abstraction.model.service;

import java.util.Scanner;

import edu.kh.oop.abstraction.model.vo.Student;

public class AbstractionService {
//	클래스는 반드시 속성,기능 둘다 작성될 필요는 없다
	public void example1() {
		Student std = new Student();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 : ");
		std.name = sc.next();
		
		System.out.print("학교명 :");
		std.schoolName =sc.next();
		
		System.out.print("학년 : ");
		std.grade = sc.nextInt();
		
		System.out.print("성별 : ");
		std.gender = sc.next().charAt(0);
		
		Student std2 = new Student();
		System.out.println("===두번째 학생정보 입력===");
		System.out.print("이름 : ");
		std2.name = sc.next();
		
		System.out.print("학교명 :");
		std2.schoolName =sc.next();
		
		System.out.print("학년 : ");
		std2.grade = sc.nextInt();
		
		System.out.print("성별 : ");
		std2.gender = sc.next().charAt(0);
//		System.out.println(std.name);
//		System.out.println(std.schoolName);
//		System.out.println(std.grade);
//		System.out.println(std.gender);
		
//		std.goToSchool();
//		std.study();
//		std.goToHome();
		std.introduce();
		std2.introduce();
	}
}
