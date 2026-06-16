package edu.kh.objarray.practice.model.service;

import java.util.Scanner;

import edu.kh.objarray.practice.model.vo.Student;

public class PracticeService {
	private Student[] students =null;
	private Scanner sc = new Scanner(System.in);
	public void start() {
		students = new Student[10];
		int sum = 0;
		
		do{
			
			System.out.print("학년 : ");
			int grade = sc.nextInt();
			System.out.print("반 : ");
			int classroom = sc.nextInt();
			sc.nextLine();
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("국어점수 : ");
			int kor = sc.nextInt();
			System.out.print("영어점수 : ");
			int eng = sc.nextInt();
			System.out.print("수학점수 : ");
			int math = sc.nextInt();
			
			students[sum] =new Student(grade, classroom, name, kor, eng, math);
		
			
			while(true) {
				System.out.print("계속 입력 하시겠습니까? (y/n)");
				char ch = sc.next().charAt(0);
				if(ch == 'n'||ch == 'N') {
					sum =10;
					break;
				}else if(ch == 'y'||ch == 'Y') {
					sum++;
					break;
				}else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요");
				}
			}
		}while(sum !=10 );
		
		
		for(Student student :students) {
			if(student == null){
			break;
			}{
			System.out.println(student.toString());
			}
		}
		
	}

		
	
}
