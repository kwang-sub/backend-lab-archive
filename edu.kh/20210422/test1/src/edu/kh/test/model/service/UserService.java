package edu.kh.test.model.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.test.model.vo.User;

public class UserService {
	private Scanner sc=new Scanner(System.in);
	private ArrayList<User> ulist = new ArrayList<User>();
	
	
	public void displayMenu()  {
		int sel=0;
		do {
			System.out.println("---사용자 관리 프로그램---");
			System.out.println("1.사용자 추가");
			System.out.println("2.전체 사용자 조회");
			System.out.println("3.사용자 삭제");
			System.out.println("0.종료");
			
			try {			
				System.out.print("메뉴 선택> ");
				sel = sc.nextInt();
				
			}catch(InputMismatchException e){
				sc.nextLine();
				System.out.println("정수만 입력해주세요");
			}
			switch(sel) {
			case 1 : addUser(); break;
			case 2 : selectAll(); break;
			case 3 : deleteUser(); break;
			case 0 : break;
			}
		}while(sel !=0);
		
		
	}
	private void addUser(){
		System.out.print("아이디를 입력해주세요 : ");
		String userId = sc.next();
		System.out.print("비밀번호를 입력해주세요 : ");
		String userPwd = sc.next();
		System.out.print("이름을 입력해주세요 : " );
		String name= sc.next();
		ulist.add(new User(userId, userPwd, name));
	}

	private void selectAll(){
		for(int i =0; i < ulist.size(); i++) {
			System.out.println(ulist.get(i));
		}
	}

	private void deleteUser(){
		System.out.println("삭제할 아이디 : ");
		String del = sc.next();
		int count =0;
		for(User u : ulist) {
			if(del.equals(u.getUserId())) {
				System.out.println(u+"사용자가 삭제되었습니다");
				ulist.remove(u);
				count ++;
				break;
			}
		}
		if(count==0) {
			System.out.println("일치하는 사용자가 없습니다");
		}
	}
}
