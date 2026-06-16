package edu.kh.hw.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import edu.kh.hw.model.vo.Member;

public class PracticeService {

	private Scanner sc= new Scanner(System.in);
	private List<Member> memList = new ArrayList<Member>(); // 회원 정보를 저장할 리스트
	private Member loginMember = null; // 로그인한 회원 정보를 저장할 변수
	
	
	public PracticeService() {  // 초기 회원 데이터를 memList에 추가
		memList.add(new Member("user01", "pass01", "유저일"));
		memList.add(new Member("user02", "pass02", "유저이"));
		memList.add(new Member("user03", "pass03", "유저삼"));
	}
	
	
	public void mainMenu() {
	
		int sel = 0;

		do {
			try {
				System.out.println();
				
				if(loginMember == null) { // 로그인이 되어있지 않은 경우
				
					System.out.println("===== 메인 메뉴 =====");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 종료");
					
					System.out.print("메뉴 선택 >> ");
				
					sel = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(sel) {
					case 1 : login(); break;
					case 2 : signUp(); break;
					case 0 : System.out.println("프로그램 종료"); break;
					default : System.out.println("메뉴에 있는 번호를 입력해주세요.");
					}
				
					
				} else { // 로그인이 되어있는 경우
					
					System.out.println("===== 로그인 메뉴 =====");
					System.out.println("1. 내 정보 조회");
					System.out.println("2. 비밀번호 변경");
					System.out.println("3. 포인트 충전");
					System.out.println("4. 로또 구매");
					System.out.println("5. 구매한 로또 번호 확인");
					System.out.println("0. 로그아웃");
					
					System.out.print("메뉴 선택 >> ");
				
					sel = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(sel) {
					case 1 : selectMyInfo(); break;
					case 2 : changePwd(); break;
					case 3 : pointCharge(); break;
					case 4 : lottoPurchase(); break;
					case 5 : checkLotto(); break;
					case 0 : 
						System.out.println("<<로그아웃>>"); 
						loginMember = null;
						sel = -1;
					break;
					default : System.out.println("메뉴에 있는 번호를 입력해주세요.");
					}
					
				}
				
			}catch (InputMismatchException e) {
				System.out.println("잘못된 값을 입력하셨습니다. 다시 시도해주세요.");
				sel = -1;
				sc.nextLine();
			}
		
		} while(sel !=0);
	}
	
	
	// 로그인
	// 아이디, 비밀번호를 입력 받아 memList에 일치하는 회원 있다면 필드 loginMember에 해당 회원 정보를 대입
	// 없을 경우 "아이디 또는 비밀번호가 일치하지 않습니다." 출력
	/*
	=== 로그인 ===
	아이디 : aaa
	비밀번호 : 123
	아이디 또는 비밀번호가 일치하지 않습니다.
	
	=== 로그인 ===
	아이디 : user01
	비밀번호 : pass01

	(로그인 메뉴 출력)
	*/
	private void login() {
		System.out.println("=== 로그인 ===");
		System.out.print("아이디 : ");
		String inId = sc.next();
		System.out.print("비밀번호 : ");
		String inPw = sc.next();
		
		Member in = new Member(inId, inPw, "확인용");
		boolean flag = false;
		for(Member m : memList) {
			if(m.getMemberId().equals(in.getMemberId()) && m.getMemberPwd().equals(in.getMemberPwd())) {
				flag = true;
			}
		}
		if(flag == true) {
			for(Member m : memList) {
				if(m.getMemberId().equals(in.getMemberId()) && m.getMemberPwd().equals(in.getMemberPwd())) {
					loginMember = m;
				}
			}	
		}else {
			System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
	}
	
	
	// 회원 가입
	// 아이디, 비밀번호, 이름을 입력 받아 생성한 Member 객체를 memList에 추가
	
	/*
	=== 회원 가입 ===
	아이디 : user04
	비밀번호 : pass04
	이름 : 유저사
	회원 가입이 완료되었습니다.
	
	(중복 아이디가 있을 경우)
	=== 회원 가입 ===
	아이디 : user01
	비밀번호 : pass04
	이름 : 유저일
	중복되는 아이디가 있습니다. 다시 시도해주세요.
	*/
	private void signUp() {
		System.out.println("=== 회원 가입 ===");
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		System.out.print("이름 : ");
		String inname = sc.next();
		int cheak = 0;
		for(Member s : memList) {
			if(s.getMemberId().equals(id)) {	
			cheak++;
			}	
		}
		if(cheak ==0) {
			memList.add(new Member(id, pw, inname));
		}else {
			System.out.println("중복되는 아이디가 있습니다. 다시 시도해주세요.");
		}
	}
	
	
	// 내 정보 조회
	/*
	=== 내 정보 조회 ===
	아이디 : user01
	이름 : 유저일
	보유 포인트 : 0
	*/
	private void selectMyInfo() {
		System.out.println("=== 내 정보 조회 ===");


		System.out.println("아이디 : "+loginMember.getMemberId());
		System.out.println("이름 : "+loginMember.getName());
		System.out.println("보유 포인트 : "+loginMember.getPoint());
		
	}
	
	
	// 비밀번호 변경
	// 현재 비밀번호, 새 비밀번호, 새 비밀번호 확인을 입력 받아
	// 현재 비밀번호가 일치하지 않는 경우 "현재 비밀번호가 일치하지 않습니다."
	// 새 비밀번호, 새 비밀번호 확인이 일치하지 않는 경우 "새 비밀번호가 일치하지 않습니다."
	// 비밀번호 변경에 성공한 경우 "비밀번호가 변경되었습니다." 출력
	/*
	=== 비밀번호 변경 ===
	현재 비밀번호 : pass01
	새 비밀번호 : 123123
	새 비밀번호 확인 : 123123
	비밀번호가 변경되었습니다.
	*/
	private void changePwd() {
		System.out.println("=== 비밀번호 변경 ===");
		System.out.print("현재 비밀번호 : ");
		String now = sc.next();
		System.out.print("새 비밀번호 : ");
		String new1 = sc.next();
		System.out.print("새 비밀번호 확인: ");
		String new2 = sc.next();
		
		if(now.equals(loginMember.getMemberPwd()) ) {
			if(new1.equals(new2)) {
				loginMember.setMemberPwd(new1); 
				System.out.println("비밀번호가 변경되었습니다.");
			}else {
				System.out.println("새 비밀번호가 일치하지 않습니다.");
			}
		}else {
			System.out.println("현재 비밀번호가 일치하지 않습니다");
		}
	}
	
	
	
	// 포인트 충전
	// 입력 받은 포인트 만큼 회원의 포인트를 충전하기
	/*
	=== 포인트 충전 ===
	충전할 금액 : 3000
	3000포인트가 충전되었습니다.
	충전 전 포인트 : 0
	충전 후 포인트 : 3000
	*/
	private void pointCharge() throws InputMismatchException{
		System.out.println("=== 포인트 충전 ===");
		System.out.print("충전할 금액 : ");
		int in = sc.nextInt();
		System.out.println(in+"포인트가 충전되었습니다.");
		System.out.println("충전 전 포인트 : " + loginMember.getPoint());
		loginMember.setPoint(loginMember.getPoint()+in);
		System.out.println("충전 후 포인트 : " + loginMember.getPoint());
		
	}
	
	
	
	// 로또 구매
	// 입력한 포인트 만큼의 로또 번호를 생성하여 로그인한 멤버의 purchaseLotto에 추가
	// 단, 0 입력 시 "구매 취소"
	// 1000원 미만 입력 시 "구매를 원할 시 1000 포인트 이상을 작성해주세요."
	// 보유 포인트보다 많은 값 입력 시 "보유한 포인트보다 많은 값을 작성할 수 없습니다." 출력
	
	/*
	=== 로또 구매 ===
	1000 포인트당 로또 1회 구매 가능
	현재 보유 포인트 : 3000
	사용할 포인트(취소 시 0 입력, 1000 단위로 작성) : 2000
	[7, 9, 10, 11, 25, 28]
	[6, 9, 20, 24, 40, 42]
	 */
	private void lottoPurchase() throws InputMismatchException{
		System.out.println("=== 로또 구매 ===");
		System.out.println("1000 포인트당 로또 1회 구매 가능");
		System.out.println("현재 보유 포인트 : " + loginMember.getPoint());
		System.out.print("사용할 포인트(취소 시 0 입력, 1000 단위로 작성) : ");
		int in = sc.nextInt();
		if(in%1000!=0) {
			System.out.println("1000원 단위로 입력해주세요");
		}else if(loginMember.getPoint()-in>=0) {
			
			loginMember.setPoint(loginMember.getPoint()-in);
			for(int i =0; i<in/1000; i++) {
				Set<Integer> lotto = new HashSet<Integer>();
				while(true) {
					
					lotto.add((int)(Math.random()*45+1));
					if(lotto.size()==6) {
						System.out.println(lotto);
						break;
					}
				}
				loginMember.getPurchaseLotto().add( lotto);
				
			}
			
		}else {
			System.out.println("포인트가 부족합니다.");
		}
		
	}
	
	
	// 구매한 로또 번호 확인
	// 지금까지 구매한 모든 로또 번호 출력
	/*
	=== 구매한 로또 번호 확인 ===
	[7, 9, 10, 11, 25, 28]
	[6, 9, 20, 24, 40, 42]
	*/
	private void checkLotto() {
		System.out.println("=== 구매한 로또 번호 확인 ===");
		for(Set<Integer> s : loginMember.getPurchaseLotto()) {
			System.out.println(s);
		}
	}
	
	
}
