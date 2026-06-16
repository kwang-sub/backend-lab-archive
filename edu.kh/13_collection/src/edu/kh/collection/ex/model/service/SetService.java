package edu.kh.collection.ex.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetService {
	
private static final boolean InputMismatchException = false;
//	Set	(집합)
//	-순서가 존재 하지 않는다.
//	-중복 데이터 저장 불가능하다.
//	-> 중복 확인을 위해서 equals()가 반드시 오버라이딩 되어있어야 한다.
//	
//	HashSet
//	- 객체 저장 시 hashCode() (해시함수)를 이용해 빠른 처리와 중복검사가 가능한 set
//	-equals()뿐만 아니라 hashCode()도 오버라이딩 되어 있어야 한다.
//	
//	1.Iterator(반복자) : 컬렉션 프레임워크에서 제공하는 컬렉션 객체 요소를 하나씩 반복 접근해 얻어오는 방법
//	TreeSet : 이진 트리 자료 구조를 이용해 저장된 데이터가 자동 정렬되는 Set(Set이기 때문에 데이터 중복 저장 불가)
	public void example1() {
		Set<String> set1 = new HashSet<String>();
		
		set1.add(new String("Apple"));
		set1.add(new String("Samsung"));
		set1.add(new String("LG"));
		set1.add(new String("Apple"));
		set1.add(new String("광"));
		set1.add(new String("섭"));
		set1.add(new String("최"));
		set1.add(new String("최광섭"));
		System.out.println(set1);
		
		Iterator<String> it = set1.iterator();
//		set1집합에 저장된 내용을 하나씩 꺼내올수 있는 형태로 변형하는 메소드
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("-----------------");
		for(String s : set1) {
			System.out.println(s);
		}
	}
	public void lotto1() {
		Set<Integer> lotto = new HashSet<Integer>();
		
		while(true) {
			int ran = (int)(Math.random()*45+1);
			lotto.add(ran);
			if(lotto.size() ==6) {
				break;
			}
		}
		System.out.println(lotto);
	}
	public void example2() {
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new LinkedHashSet<String>();
		
		for(int i =0; i<20; i++) {	
			set2.add("a" +i);
			set1.add("a"+i);
		}
		System.out.println(set1);
		System.out.println(set2);
		Iterator<String> it = set1.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("=============");
		for(String s : set2) {
			System.out.println(s);
		}
	}
	public void lotto2() {
		Set<Integer>lotto = new TreeSet<Integer>();
		while(true) {
			int ran=(int)(Math.random()*45+1);
			lotto.add(ran);
			if(lotto.size()==6) {
				break;
			}
		}
		System.out.println(lotto);
	}
	public void example3() {
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			try{
				
				System.out.print("금액입력 : ");
				int in = sc.nextInt();
			if(in<1000) {
				System.out.println("1000원 이상 입렵해주세요");
			}else if(in >100000) {
				System.out.println("10만원 이하 까지만 구매 가능합니다");
			}else {
				
				List<Set<Integer>> sum =new ArrayList<Set<Integer>>();
				
				for(int i = 0; i<(in/1000); i++) {
					Set<Integer>lotto = new TreeSet<Integer>();
					while(true) {
						lotto.add((int)(Math.random()*45+1));
						if(lotto.size()==6) {
							break;
						}
					}
					sum.add(lotto);
				}
				for(Set<Integer> s : sum) {
					System.out.println(s);
				}
				break;
			}
			
			}catch(InputMismatchException e) {
				System.out.println("타입이 잘못 입력되었습니다.");
			}
		}
	}
}
