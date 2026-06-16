package kr.co.billiejoe.member.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.billiejoe.member.exception.SaveFileException;
import kr.co.billiejoe.member.model.dao.MemberDAO;
import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.place.model.vo.MyReservation;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 로그인 Service
	@Override
	public Member login(Member inputMember) {
		
		// 암호화
		String encPw = bCryptPasswordEncoder.encode(inputMember.getMemberPw());
		
		Member loginMember = dao.login(inputMember.getMemberEmail());
		
		if(loginMember != null) {
			
			if( !bCryptPasswordEncoder.matches(inputMember.getMemberPw(), loginMember.getMemberPw()) ) {
				loginMember = null;
			
			}else {
				loginMember.setMemberPw(null); 
			}
		
		}	
		
		return loginMember;
	}

	// 이메일(ID) 중복검사 Service
	@Override
	public int emailDupCheck(String email) {
		return dao.emailDupCheck(email);
	}

	// 회원 가입 Service
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int signUp(Member inputMember, MultipartFile images, String webPath, String savePath) {
		
		// 비밀번호 암호화
		String encPwd = bCryptPasswordEncoder.encode( inputMember.getMemberPw() );
		inputMember.setMemberPw(encPwd);
		
			// 업로드한 프로필 사진이 있을 경우 
			if( !images.getOriginalFilename().equals("") ) {
				// DB에 저장할 이미지 파일명을 가공 및 inputMember에 세팅
				String fileName = rename( images.getOriginalFilename() );
				String pictureADDR = webPath+fileName;
				inputMember.setMemberPic(pictureADDR);
				
				// 파일저장
				try {
					images.transferTo(new File( savePath + fileName ));
					
				} catch (Exception e) {
					e.printStackTrace();
					
					throw new SaveFileException();
					
				}
				
			}else { // 업로드한 프로필 사진이 없는 경우(null방지)
				inputMember.setMemberPic("resources/images/member.jpg");
			}
			
			int result = dao.signUp(inputMember);
		
		return result; 
	}
	
	// 파일명 변경 메소드
	private String rename(String originFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new java.util.Date(System.currentTimeMillis()));
		
		int ranNum = (int)(Math.random()*100000); // 5자리 랜덤 숫자 생성
		
		String str = "_" + String.format("%05d", ranNum);
		
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		return date + str + ext;
	}

	
	// 회원 탈퇴 Service
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int secession(String memberPw,  Member loginMember) {
		String savePwd = dao.selectPassword(loginMember.getMemberNo());
		
		int result = 0; // 결과 저장용 변수
		
		// 조회한 비밀번호와 입력 받은 현재 비밀번호가 일치하는지 확인
		if( bCryptPasswordEncoder.matches(memberPw, savePwd) ) {
			
			// 예약건이 존재하는지 확인
			int count = dao.selectReservation(loginMember.getMemberNo());
			
			if(count == 0) {
				result = dao.secession(loginMember.getMemberNo());
			}
		}
		return result; // 탈퇴 실패
	}

	// 회원정보 수정 Service
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateMember(Member inputMember, MultipartFile image, String savePath) {
		
		String fileName = null;
		if(!image.getOriginalFilename().equals("")) { // 이미지가 업로드된 경우
			fileName = "resources/images/" + rename(image.getOriginalFilename());
			// 웹 접근 경로 + 변경된 파일명
			inputMember.setMemberPic(fileName);
		}
		
		int result = dao.updateMember(inputMember);
		
		if(result > 0) { // DB MEMBER 테이블 수정 성공 시 
			
			if(fileName != null) { // 업로드된 이미지가 있을 때
				// 이 때 파일을 서버에 저장
				try {
					image.transferTo(new File(savePath + "/" + fileName));
				}catch (Exception e) {
					e.printStackTrace();
					throw new SaveFileException();
				}
			}
			
		}
		
		return result;
	}

	// 회원 비밀번호 변경 Service
		@Transactional(rollbackFor=Exception.class)
		@Override
		public int changePwd(String currentPwd, String newPwd, Member loginMember) {

			// 1) 암호화를 먼저 고려해야한다.
			// - 현재 비밀번호가 일치하는지 확인 먼저 진행해야 한다!
			// --> 왜? bcrypt 암호화를 사용하고 있기 때문에
			//  -- 왜죠? bcrypt 암호화는 비밀번호를 조회해온 후 matches() 메소드로 비교해야함
			
			// DB에 저장되어 있는 현재 회원의 비밀번호 조회
			String savePwd = dao.selectPassword(loginMember.getMemberNo());
			
			int result = 0; // 결과 저장용 변수
			
			// 조회한 비밀번호와 입력 받은 현재 비밀번호가 일치하는지 확인
			if( bCryptPasswordEncoder.matches(currentPwd, savePwd)) {
				
				// 2) 비밀번호 변경
				// - 새 비밀번호를 암호화
				String encPwd = bCryptPasswordEncoder.encode(newPwd);
				
				// 마이바티스 메소드는 SQL 수행 시 사용할 파라미터를 
				// 하나만 추가할 수 있다! -> loginMember에 담아서 전달
				loginMember.setMemberPw(encPwd);
				
				result = dao.changePwd(loginMember);
				
				// loginMember에 저장한 encPwd를 제거 (Session에 비밀번호 저장하면 안된다!)
				loginMember.setMemberPw(null);
				
			}
			
			return result;
		}

		// 이용 예정인 공간 조회
		@Override
		public MyReservation selectLatestPlace(int memberNo) {
			return dao.selectLatestPlace(memberNo);
		}

		
		// 내 장소에 예약된 장소 조회
		@Override
		public List<MyReservation> selectReservedPlace(int memberNo) {
			return dao.selectReservedPlace(memberNo);
		}

	
	
	
	

}
