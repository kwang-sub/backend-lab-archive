package kr.co.billiejoe.member.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.place.model.vo.MyReservation;

public interface MemberService {

	/** // 로그인 Service
	 * @param inputMember
	 * @return loginMember
	 */
	public abstract Member login(Member inputMember);

	/** 이메일(ID) 중복검사
	 * @param email
	 * @return result
	 */
	public abstract int emailDupCheck(String email);

	/** 회원 가입 Service
	 * @param inputMember
	 * @param savePath 
	 * @param webPath 
	 * @param images 
	 * @return result
	 */
	public abstract int signUp(Member inputMember, MultipartFile images, String webPath, String savePath);

	/** 회원 탈퇴 Service
	 * @param currentPwd
	 * @param loginMember
	 * @return result
	 */
	public abstract int secession(String memberPw, Member loginMember);

	/** 회원 정보 수정
	 * @param inputMember
	 * @param image
	 * @param savePath
	 * @return result
	 */
	public abstract int updateMember(Member inputMember, MultipartFile image, String savePath);

	/** 비밀번호 변경 
	 * @param currentPwd
	 * @param newPwd
	 * @param loginMember
	 * @return result
	 */
	public abstract int changePwd(String currentPwd, String newPwd, Member loginMember);

	/** 이용 예정인 공간 조회
	 * @param memberNo
	 * @return latestPlace
	 */
	public abstract MyReservation selectLatestPlace(int memberNo);

	/** 내 장소에 예약된 공간 조회
	 * @param memberNo
	 * @return reservedPlace
	 */
	public abstract List<MyReservation> selectReservedPlace(int memberNo);


}
