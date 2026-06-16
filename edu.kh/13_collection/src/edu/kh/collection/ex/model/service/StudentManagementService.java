package edu.kh.collection.ex.model.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.kh.collection.ex.model.vo.Student;

public class StudentManagementService {
	private Scanner sc = new Scanner(System.in);
	
	private List<Student> stdList = new LinkedList<Student>();
//	ArrayList, LinkedList, Vetor등 모두 List를 구현한 자식 클래스로 동일한 메소드를 가지고 있다.
//	인터페이스+다형성 사용시 나타나는 이점
	
	public void displayMenu() {
		int sel = 0;
		do {
			System.out.println("==========학생 관리 프로그램===========");
			System.out.println("1.학생 전체 조회");
			System.out.println("2.학생 정보 추가");
			System.out.println("3.학생 정보 수정");
			System.out.println("4.학생 정보 제거");
			System.out.println("5.이름 검색");
			System.out.println("0.종료");
			System.out.print("메뉴 선택 >> ");
			try {
				sel = sc.nextInt();
				sc.nextLine();			
				switch(sel) {
				case 1 : selectAll();break;
				case 2 : addStudent();break;
				case 3 : updateStudent();break;
				case 4 : deleteStudent();break;
				case 5 : serchName();break;
				case 0 : System.out.println("프로그램 종료");break;
				default : System.out.println("메뉴에 있는 숫자만 입력해주세요.");
				}
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요");
				sel =-1;
				sc.nextLine();
			}
		}while(sel != 0 );
	}
	private void selectAll() {
		System.out.println("[학생 전체 조회]");
		if(stdList.isEmpty()) {
			System.out.println("입력된 학생이 없습니다.");
		}else {
			for(int i=0; i<stdList.size(); i++) {
				Student s = stdList.get(i);
				System.out.printf("%d번째 %s %d %d\n", i+1,s.getAge(),s.getAge(),s.getGrade());
			}
		}
		
	}
	
	public Student createStuden() throws InputMismatchException {
		
		System.out.print("이름 입력 : ");
		String name = sc.nextLine();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
	
		System.out.print("학년 : ");
		int grade = sc.nextInt();
		sc.nextLine();
		return new Student(name, age, grade);
	}
	private void addStudent() throws InputMismatchException {
		System.out.println("[학생 정보 추가]");
		stdList.add(createStuden());
	}
	private void updateStudent() throws InputMismatchException {
		System.out.println("[학생 정보 수정]");
		System.out.print("수정할 인덱스 입력 : ");
		int in = sc.nextInt();
		sc.nextLine();
		if(!stdList.isEmpty() && in >= 0 && in < stdList.size()) {
			
			Student s = createStuden();
			stdList.set(in, s);
			System.out.println("수정되었습니다.");
		}else {
			System.out.println("학생 정보가 없거나, 인덱스가 잘못 입력되었습니다.");
		}
	}
	private void deleteStudent() throws InputMismatchException {
		System.out.println("[학생 정보 삭제]");
		System.out.print("삭제할 인덱스 입력 : ");
		int in = sc.nextInt();
		if(!stdList.isEmpty() && in>=0 && in<stdList.size()) {
			System.out.println(stdList.remove(in)+" 삭제 되었습니다.");
		}else {
			System.out.println("학생 정보가 없거나, 인덱스가 잘못 입력되었습니다.");
		}
	}
	private void serchName() {
		System.out.print("이름 입력 : ");
		String in = sc.next();
		
		
		for(Student s : stdList) {
			if(!stdList.isEmpty()&&s.getName().contentEquals(in)) {
				System.out.println(s);
			}
		}
		
	}
}
