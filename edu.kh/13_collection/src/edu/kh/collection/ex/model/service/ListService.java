package edu.kh.collection.ex.model.service;

import java.util.ArrayList;
import java.util.List;

import edu.kh.collection.ex.model.vo.Student;

//	Collection : 자바에서 제공하는 자료구조를 담당하는 프레임워크
// 	콜렉션 장점
//	1.크기 제약이 없다.
//  2.추가, 삭제, 검색, 정렬등의 기능이 내장되어 있다.
//	3.여러 데이터 타입을 저장할 수 있다. (단 기본 자료형은 Wrapper Class로 변형해서 저장한다.)
//
//
//	List : 순서가 유지되며 중복 데이터 저장이 가능하다.(데이터가 순차적으로 나열된 배열과 비슷한 모양)
//	1.add(E e) : 리스트의 끝에 인스턴스 데이터를 추가하는 메소드
//	2.set(int index,E e ) : List 내의 index번째 요소를 e로 변경
//	3.Object remove(int index) : List 내의 index번째 요소를 제거하고 중간에 빈칸이 없도록 index번째 뒤에 요소들을 한칸씩 당긴다.
//
//	4.Generics(제네릭) <E> : 클래스, 메소드, 컬렉션 내부에 사용될 클래스 타입을 한 종류로 제한하는 기능. 컬렉션에 여러 타입이 저장되면 필요시 마다 타입검사, 형변환이 수시로 일어나기 때문에 한가지 타입으로 제한하는것이다.
//	제네릭 장점
//	-하나의 타입으로 제한되기 때문에 타입검사, 형변환이 필요없다.
//	-하나의 코드로 여러 타입 처리가 가능하다.
//	5.int indexOf(object o) : o가 참조하는 객체와 같은 객체가 List에 있으면 확인하여 있으면 같은 객체의 index 없으면-1을 반환한다.

public class ListService {
	public void example1() {
//		ArrayList : List의 후손, 배열처럼 사용 가능하지만 배열의 단점이 보완되어있다.
		ArrayList list = new ArrayList();  //초기용량 10
		
		ArrayList list2 = new ArrayList(3);
		
		list2.add("Hello World");
		list2.add("배고파요");
		list2.add(10);
		list2.add(3.14);
		System.out.println(list2.toString());
		for(Object l2 : list2) {
			System.out.println(l2);
		}
		System.out.println("-----------------");
		System.out.println("[일반 for문 출력]");
		System.out.println("size : " + list2.size());
		for(int i=0; i<list2.size(); i++) {
			System.out.println(list2.get(i));
		}
		Object obj = list2.set(1, new Student("홍길동", 12, 1));
		System.out.println("반환된 : " + obj);
		System.out.println(list2);
		Object obj2 = list2.remove(2);
		System.out.println("제거된 2번 인덱스 내용 : " + obj2);
		System.out.println(list2);
		
		for(Object o : list2) {
			if(o instanceof String) {
				System.out.println("String : " + o);
			}else if(o instanceof Double) {
				System.out.println("Double : " + o);
			}else if(o instanceof Student) {
				System.out.println("Student : " + ((Student)o).getName());
			}
		}
		
	}
	public void example2() {
		List<Student> list = new ArrayList<Student>();
//		ArrayList는 인터페이스 List를 상속받아 구현한 클래스 (업캐스팅)
		list.add(new Student("김세영", 17, 1));
		list.add(new Student("최광섭", 14, 2));
		list.add(new Student("홍길동", 19, 3));
		for(Student s : list) {
			System.out.println(s.getName());
		}
		Student removeStudent = list.remove(1);
		System.out.println("삭제학생 : " + removeStudent);
		for(Student s : list) {
			System.out.println(s);
		}
		int index = list.indexOf(new Student("홍길동", 19, 3));
		System.out.println("신사임당의 index : " + index);
		
		System.out.println("list는 비어있는가 ? " + list.isEmpty());//리스트 비어있는지 확인
		list.clear();
		System.out.println("clear()후 list는 비어있는가 ? " + list.isEmpty());//리스트 비어있는지 확인
		
	}
}
