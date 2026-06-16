package edu.kh.poly.practice.model.service;

import java.util.Scanner;

import edu.kh.poly.practice.model.vo.Employee;
import edu.kh.poly.practice.model.vo.Person;
import edu.kh.poly.practice.model.vo.Student;

public class PracticeService implements QuestionInterface{
	
	private Scanner sc = new Scanner(System.in);
	
	private Person[] pArr = new Person[3];

	@Override
	public  void displayMenu() {
		
		
		int sel = 0;
		
		do {
			System.out.println("===== 종합 문제 =====");
			System.out.println("1. Person 배열 pArr에 학생 또는 직원 등록하기.");
			System.out.println("2. pArr 배열에 저장되어있는 모든 요소 출력하기.");
			System.out.println("3. pArr배열에서 학생 정보만 출력하기");
			System.out.println("4. pArr배열에서 직원 정보만 출력하기");
			System.out.println("5. pArr배열에서 입력 받은 이름과 같은 사람 정보를 얻어와 출력하기");
			System.out.println("6. 모든 사람의 이름을 한 줄로된 문자열로 얻어와 출력하기");
			System.out.println("7. 모든 사람의 나이 합과, 최고/최저 연령 출력하기");
			System.out.println("8. 특정 인덱스에 있는 사람의 정보를 삭제하고,삭제된 사람 정보를 얻어와 출력하기");
			System.out.println("9. 배열 크기를 1칸 늘리고, 늘어난 칸에 새로운 학생 또는 직원  등록하기");
			System.out.println("0. 종료");
			
			System.out.print("메뉴선택 >>");
			sel = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			switch (sel) {
			case 1: insertPerson();break;
			case 2: selectAll();break;
			case 3: selectStudent();break;
			case 4: selectEmployee();break;
			case 5: 
				Person searchPerson = searchName();
				if(searchPerson == null) {
					System.out.println("일치하는 이름의 사람이 없습니다.");
				}else {
					System.out.println(searchPerson);
				}
				
				break;
				
			case 6: System.out.println(printName());break;
			case 7: printAge();break;
			case 8: Person dp = deletePerson();
				if(dp == null) {
					System.out.println("삭제할 데이터가 없습니다.");
				}else {
					System.out.println(dp + "정보가 삭제 되었습니다.");
				}
				break;
			case 9: addPerson(); break;
			case 0:  break;
			
			
			default: System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
			
			
		}while(sel != 0);
	}

	@Override
	public void insertPerson() {
		for(int i=0; i<pArr.length; i++) {
			System.out.print("1.학생 / 2.직원(선택) : ");
			int sel = sc.nextInt();
			sc.nextLine();
			if(sel == 1) {
				System.out.println("===학생 정보 입력===");
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("나이 : ");
				int age = sc.nextInt();
				System.out.print("학년 : ");
				int grade = sc.nextInt();
				System.out.print("반 : ");
				int classroom = sc.nextInt();
				
				pArr[i] = new Student(name, age, grade, classroom);
			
			}else if(sel == 2) {
				System.out.println("===직원 정보 입력===");
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("나이 : ");
				int age = sc.nextInt();
				System.out.print("사번 : ");
				int empNo = sc.nextInt();
				System.out.print("직급 : ");
				String position = sc.next();
				System.out.print("급여 : ");
				int salary = sc.nextInt();
				
				pArr[i] = new Employee(name, age, empNo, position, salary);
				
			}else {
				pArr[i] = null;
			}
		}
	}
	// 2.pArr 배열에 저장되어있는 모든 요소 출력하기.	
//	배열에 값이 이력되지 않은 경우 "배열에 저장된 정보가 없습니다."
	@Override
	public void selectAll() {
		for(int i = 0; i<pArr.length; i++) {
			if(pArr[i]==null) {
				System.out.println("배열요소에 저장된 정보가 없습니다.");
			}else {
				
				System.out.println(pArr[i].toString());
			}
		}
		
	}
	// 3. pArr배열에서 학생 정보만 출력하기
	@Override
	public void selectStudent() {
		int count = 0;
		for(Person p : pArr) {
			if(p != null && p instanceof Student) {
				System.out.println(p);
				count++;
			}
		}
		if(count == 0 ) {
			System.out.println("학생 정보가 없습니다.");
		}
			
	}
	// 4. pArr배열에서 직원 정보만 출력하기
	@Override
	public void selectEmployee() {
		int count = 0;
		for(Person p : pArr) {
			if(p != null && p instanceof Employee) {
				System.out.println(p);
				count++;
			}
		}
		if(count == 0 ) {
			System.out.println("사원 정보가 없습니다.");
		}
		
	}
	// "5. pArr배열에서 입력 받은 이름과 같은 사람 정보를 얻어와 출력하기"
	@Override
	public Person searchName() {
		System.out.print("이름 입력 : ");
		String name = sc.next();
		for(Person p : pArr) {
			if(p != null && name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}

	@Override
	public String printName() {
		String name ="";
		for(int i = 0; i<pArr.length; i++) {
			if(pArr[i] != null) {
				name += pArr[i].getName();
				if(i < pArr.length-1) {
					name += ", ";
				}
			}	
		}
		return name;
	}

	@Override
	public void printAge() {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(Person p : pArr) {
			sum += p.getAge();
			if(max<p.getAge()) {
				max = p.getAge(); 
			}else if (min>p.getAge()) {
				min=p.getAge();
			}
		}
		System.out.println("모든 사람의 나이 합 : " + sum);
		System.out.println("최고 연령 : " + max);
		System.out.println("최저 연령 : " + min);
	}

	
	// 8. 특정 인덱스에 있는 사람의 정보를 삭제하고,
	// 삭제된 사람 정보를 얻어와 출력하기
	// ex)
	// 삭제할 인덱스 : 0
	// 0번 인덱스에 있던 홍길동 / 20 / 3 / 5 정보가 삭제되었습니다
	@Override
	public Person deletePerson() {
		
		Person str =  null;
		
		System.out.println("삭제할 인덱스 : ");
		int in = sc.nextInt();
		if(in >=0 && in<pArr.length) {
			if(pArr[in] != null)
			 str = pArr[in];
			pArr[in] =null;
		}
		
		return str;
	}

	@Override
	public void addPerson() {
		Person[] newArr = new Person[pArr.length + 1];
		for(int i = 0;  i < pArr.length; i++) {
			
			newArr[i] = pArr[i];
		}
	      int sel = 0;
	      do {
	    	  System.out.print("1.학생 / 2.직원 (선택) : ");
	    	  sel = sc.nextInt();
	    	  sc.nextLine();
	         if(sel == 1) { // 학생 정보(이름, 나이, 학년, 반) 입력 받기
	            System.out.println("=== 학생 정보 입력 ===");
	            
	            System.out.print("이름 : ");
	            String name = sc.next();
	            
	            System.out.print("나이 : ");
	            int age = sc.nextInt();
	            
	            System.out.print("학년 : ");
	            int grade = sc.nextInt();
	            
	            System.out.print("반 : ");
	            int classroom = sc.nextInt();
	            sc.nextLine();
	            
	            pArr[pArr.length - 1] = new Student(name, age, grade, classroom);
	            
	         }else if(sel == 2) { // 직원 정보(이름, 나이, 사번, 직급, 급여) 입력 받기
	            System.out.println("=== 직원 정보 입력 ===");
	            
	            System.out.print("이름 : ");
	            String name = sc.next();
	            
	            System.out.print("나이 : ");
	            int age = sc.nextInt();
	            
	            System.out.print("사번 : ");
	            int empNo = sc.nextInt();
	            
	            System.out.print("직급 : ");
	            String position = sc.next();
	            
	            System.out.print("급여: ");
	            int salary = sc.nextInt();
	            sc.nextLine();
	            
	            pArr[newArr.length - 1] = new Employee(name, age, empNo, position, salary);
	            
	         }else { // 다른 숫자를 입력한 경우
	            System.out.println("잘못입력하셨습니다. ");
	         }
	         
	      }while( !(sel == 1 || sel == 2) );
		
	}

	
	
}
