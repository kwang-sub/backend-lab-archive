package kr.co.billiejoe.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.place.model.vo.MyReservation;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 로그인 DAO
	 * @param memberEmail
	 * @return loginMember
	 */
	public Member login(String memberEmail) {
		return sqlSession.selectOne("memberMapper.login", memberEmail);
	}

	/** 이메일(ID) 중복 검사 DAO
	 * @param email
	 * @return result
	 */
	public int emailDupCheck(String email) {
		return sqlSession.selectOne( "memberMapper.emailDupCheck" , email );
	}

	/** 회원 가입 DAO
	 * @param inputMember
	 * @return result
	 */
	public int signUp(Member inputMember) {
		return sqlSession.insert("memberMapper.signUp" , inputMember );
	}
	
	/** 회원 비밀번호 조회 DAO
	 * @param memberNo
	 * @return result
	 */
	public String selectPassword(int memberNo) {
		return sqlSession.selectOne("memberMapper.selectPassword" , memberNo );
	}

	/** 회원 탈퇴 DAO
	 * @param loginMember
	 * @return result
	 */
	public int secession(int memberNo) {
		return sqlSession.update("memberMapper.secession" , memberNo );
	}

	/** 탈퇴 회원 목록 추가 DAO
	 * @param loginMember
	 * @return 
	 */
	public int secesionInsert(Member loginMember) {
		return sqlSession.insert("memberMapper.secessionInsert" , loginMember );
		
	}

	/** 탈퇴 예정 회원의 예약건 조회
	 * @param memberNo
	 * @return result3
	 */
	public int selectReservation(int memberNo) {
		return sqlSession.selectOne("memberMapper.selectReservation", memberNo);
	}

	/** 회원 정보 수정 DAO
	 * @param inputMember
	 * @return result
	 */
	public int updateMember(Member inputMember) {
		return sqlSession.update("memberMapper.updateMember", inputMember);
	}

	/** 비밀번호 변경 DAO
	 * @param loginMember
	 * @return result
	 */
	public int changePwd(Member loginMember) {
		return sqlSession.update("memberMapper.changePwd", loginMember);
	}

	/** 이용 예정인 공간 조회
	 * @param memberNo
	 * @return latestPlace
	 */
	public MyReservation selectLatestPlace(int memberNo) {
		return sqlSession.selectOne("memberMapper.selectLatestPlace", memberNo);
	}

	/** 내 장소에 예약된 공간 조회
	 * @param memberNo
	 * @return reservedPlace
	 */
	public List<MyReservation> selectReservedPlace(int memberNo) {
		return sqlSession.selectList("memberMapper.selectReservedPlace", memberNo);
	}
	
	
	

}