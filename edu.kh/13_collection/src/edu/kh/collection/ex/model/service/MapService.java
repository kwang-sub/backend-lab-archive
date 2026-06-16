package edu.kh.collection.ex.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;

import edu.kh.collection.ex.model.vo.Student;

public class MapService {
	
//	Map : Key와 Value 한 쌍이 하나의 데이터가 되어 이를 모아둔 객체
//	Key는 중복을 허용하지 않고  Map 내부에 순서없이 정렬된다.(Set의 특징)
//	Value는 Key에 의해서 중복되는 값이 저장되어도 구분이 된다.(List의 특징)
//	Map은 중복되는 키를 가진 데이터가 들어오면 중복으로 판단해 제거하는 것이 아닌 키는 유지하되 Value만 새로운 데이터로 변경
//	HashMap : Key에 해당되는 객체가 hashCode(), equals() 오버라이딩 되어야한다.
//	Set<E> = keySet() : Map 데이터중 Key만 하나의 집합으로 만들어 반환\
//	E = get(Object key) : Map에서 key 값이 일치하는 데이터의 value를 반환
//	Set<Map.Entry<K, V>> 참조변수 = 객체.entrySet() : Map을 키와 값 쌍으로 구성된 Map.Entry Set 객체에 담아 반환 (Map.Entry == K,V 쌍을 한번에 나타내는 객체)
	public void exameple1(){
		Map<String, Student> map = new LinkedHashMap<String, Student>();
		
		Student std1 = new Student("홍길동",17,1);
		map.put("H001", std1);
		map.put("M001", new Student("신사임당", 15, 2));
		map.put("H002", new Student("최광섭", 28, 1));
		map.put("H003", new Student("김세영", 26, 1));
		
		System.out.println(map);
		Set<String> set = map.keySet();
		
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			Student value = map.get(key);
			
			System.out.println(key+ " = " + value);
		}
	}
	public void example2() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		int number = 1;
		map.put(number++, "김밥");
		map.put(number++, "라면");
		map.put(number++, "김치찌개");
		map.put(number++, "삼겹살");
		System.out.println(map);
		map.put(1, "치킨");
		System.out.println(map);
		
		
		Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
		Iterator<Map.Entry<Integer, String>> it = entrySet.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, String> entry = it.next();
			System.out.println(entry.getKey() + " = " + entry.getValue());

		}
		System.out.println("===========");
		for(Entry<Integer, String> s : entrySet) {
			
			System.out.println(s.getKey() + " = " + s.getValue());
		}
		
		
	}
//	public void d() {
//		Scanner sc = new Scanner(System.in);
//		int num = 0;
//		do{
//			System.out.println("로그인  ");
//			System.out.println("회원가입  ");
//			try {
//				System.out.println("번호입력 : ");
//				num = sc.nextInt();				
//			}catch(InputMismatchException e) {
//				System.out.println("정수 입력해주세요");
//			}
//			switch(num) {
//				case 1 : break;
//				case 2 : break;
//				default : System.out.println("정수 입력해주세요");
//			}
//			
//		}while(num ==0);
//	}
//	public void 회원가입() {
//		ArrayList<String> kwang = new ArrayList<String>();
//		Scanner sc = new Scanner(System.in);
//		System.out.println("아이디 입력해주세요 : ");
//		String id = sc.next();
//		System.out.println("비밀번호 입력 : ");
//		String pw = sc.next();
//		
//		
//	}
}
