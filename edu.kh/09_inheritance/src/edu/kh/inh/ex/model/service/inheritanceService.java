package edu.kh.inh.ex.model.service;

import edu.kh.inh.ex.model.vo.Employee;
import edu.kh.inh.ex.model.vo.Person;
import edu.kh.inh.ex.model.vo.Student;

public class inheritanceService extends Student {
	public void example1() {
		
		Person p = new Person();
		p.setName("홍길동");
		p.setAge(20);
		
		Student std = new Student();
		std.setName("김철수");
		std.setAge(15);
		std.setGrade(2);
		std.setClassroom(3);
		
		
		
		Employee emp = new Employee();
		emp.setName("유관순");
		emp.setAge(18);
		emp.setPosition("부장");
		emp.setEmpNo(1);
		emp.setSalary(4000000);
		
		
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(emp.getName());
		System.out.println(emp.getAge());
		
		
		Student std2 = new Student("광", 28, 3, 1);
		System.out.println(std2.getName());
		System.out.println(std2.getAge());
		System.out.println(std2.getGrade());
		System.out.println(std2.getClassroom());
		
	}
	public void example2() {
		Person p = new Person("홍길동", 20);
		Employee e = new Employee("고길동", 40, 10, "부장", 500);
		System.out.println(p.toString());
		System.out.println(p);
//		print 관련 메소드의 매개변수로 참조변수들이 작성된 경우 자동으로 참조변수가 참조하는 객체의 toString() 메소드를 호출한다.
		System.out.println(e);
		Student std = new Student("김이박", 19, 3, 5);
		System.out.println(std);
		std.test();
		std.test("국어");
		System.out.println(std.test("국어", "수학"));
		
		System.out.println(test(10));
	}
	public void example3() {
		Student s1 = new Student("홍길동", 18, 2, 7);
		Student s2 = new Student("홍길동", 18, 2, 7);
		Student s3 = s1;
		Student s4 = new Student("홍길동", 18, 2, 8);
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s3);
		
	}
}
