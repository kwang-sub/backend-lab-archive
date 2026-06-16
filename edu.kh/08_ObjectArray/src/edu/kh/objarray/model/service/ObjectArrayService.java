package edu.kh.objarray.model.service;

import java.util.Scanner;

import edu.kh.objarray.model.vo.Book;

public class ObjectArrayService {
	private Book[] books = null;
	private Scanner sc = new Scanner(System.in);
	
	
	public void displayMenu() {
		int sel = 0;
		do {
			System.out.println("==================");
			
			System.out.println("1.초기화1");
			System.out.println("2.초기화2");
			System.out.println("3.모든 책 정보 출력");
			System.out.println("4.모든 책 제목만 출력");
			System.out.println("5.저자 검색");
			System.out.println("6.10%씩 가격 인하");
			System.out.println("0.프로그램 종료");
			System.out.println("==================");
			System.out.print("메뉴 선택 : ");
			sel = sc.nextInt();
			sc.nextLine();
//			같은 클래스 내부의 필드 메소드는 단순히 이름을 작성하는 것으로 호출이 가능하다.
			switch(sel) {
			case 1 : init1();break;
			case 2 : init2();break;
			case 3 : printBooks();break;
			case 4 : System.out.println(printTitle());break;
			case 5 : System.out.println(searchAuthor());break;
			case 6 : priceCut(10);break;
			
			case 0 : System.out.println("프로그램을 종료합니다"); break;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}while(sel != 0);
	}
	
	public void init1() {
//		3칸짜리 Book 배열을생성하여 각 배열 요소를 초기화
		
		books = new Book[3];
		
		books[0] = new Book();
		books[0].setTitle("자바의 정석");
		books[0].setContent("자바의 대한 내용이 적혀있음.");
		books[0].setAuthor("남궁성");
		books[0].setPublisher("도우출판");
		books[0].setPrice(30000);
				
		books[1] = new Book("어린왕자", "어린왕자의 이야기", "생택쥐베리", "교학", 10000);
		books[2] = new Book("삼국지", "도원결의", "이문열", "알에이치코리아", 1300000);
		
		
		
	}
	public void printBooks() {
//		System.out.println(books[0].toString());
//		System.out.println(books[1].toString());
//		System.out.println(books[2].toString());
//
//		for(int i = 0; i<books.length; i++) {
//			System.out.println(books[i].toString());
//		}
//		3.향상된 for문 (for each문)
//		처음부터 끝까지 배열,컬렉션등의 모든 요소를 접근할때 사용
//		for(배열에서 얻어온 값을 저장할 변수 :배열명){
//		반복 수행할 구문
//		}
		for(Book book : books) {
			System.out.println(book.toString());
		}
		
	}
	public void init2() {
	
		books = new Book[5];
		for(int i=0; i<books.length; i++) {
			System.out.print("제목 : ");
			String title = sc.nextLine();
			System.out.print("내용 : ");
			String content = sc.nextLine();
			System.out.print("저자 : ");
			String author = sc.nextLine();
			System.out.print("출판사 : ");
			String publisher = sc.nextLine();
			System.out.print("가격 : ");
			int price = sc.nextInt();
			sc.nextLine();//입력문자에 남은 개행문자 제거
			
			books[i] = new Book(title, content, author, publisher, price);
		}
	}
	public String printTitle() {
		
		String str = "";
		for(Book book : books) {
			str+=book.getTitle()+"\n";
			
		}
		return str;
	}
	public String searchAuthor() {
		
		while(true) {
			System.out.print("저자입력 : ");
			String in = sc.nextLine();
		
			for(Book book : books) {
				if(book.getAuthor().equals(in)) {
					return book.toString();
				}
			}
			System.out.println("다시 입력해주세요");
		}

	}
	public void priceCut (int cut) {
//		System.out.print("할인율(1~99) : ");
//		int cut = sc.nextInt();
		
		if (cut>0 && cut<100) {
			for(Book book:books) {
			book.setPrice((int)(book.getPrice()*(1-(cut/100.0) )));
				
			}
		}else {
			System.out.println("1~99사이의 수를 입력하세요");
		}
	}
}
