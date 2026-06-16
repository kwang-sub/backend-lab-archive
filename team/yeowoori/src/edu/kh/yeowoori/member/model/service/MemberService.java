package edu.kh.yeowoori.member.model.service;
import static edu.kh.yeowoori.common.JDBCTemplate.close;
import static edu.kh.yeowoori.common.JDBCTemplate.commit;
import static edu.kh.yeowoori.common.JDBCTemplate.getConnection;
import static edu.kh.yeowoori.common.JDBCTemplate.rollback;
import static edu.kh.yeowoori.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.yeowoori.member.model.dao.MemberDAO;
import edu.kh.yeowoori.member.model.vo.Member;
public class MemberService {
	private MemberDAO dao = new MemberDAO();

	/** 로그인 Service
	 * @param inputId
	 * @param inputPw
	 * @return
	 * @throws Exception
	 */
	public Member login(String inputId, String inputPw) throws Exception {
		Connection conn = getConnection();
		Member loginMember = dao.login(conn, inputId, inputPw);
		close(conn);
		
		return loginMember;
	}

	/** 비밀번호 변경 결과 Service
	 * @param currentPwd
	 * @param newPwd1
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePwd(String currentPwd, String newPwd1, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.changePwd(conn, currentPwd, newPwd1, memberNo);
		
		if(result > 0)  commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	/** 회원 가입 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{

		// 1) 커넥션 얻어오기
		Connection conn = getConnection();
		
		// 2) DAO 호출해서 insert 진행 후 결과 반환받기
		int result = dao.signUp(conn, member);
		
		// 3) 반환 받은 결과에 따라 트랜잭션 처리하기
		if(result > 0 ) commit(conn);
		else			rollback(conn);
		
		// 4) 사용한 커넥션 반환하기
		close(conn);
		
		// 5) 결과를 Controller로 반환하기
		return result;
	}

	/** 아아디 중복 검사 Service
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String id) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(conn, id);
		
		close(conn);
		
		return result;
	}

	/** 회원 정보 수정 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int memberUpdate(Member member) throws Exception{
	Connection conn = getConnection();
			
			int result = dao.memberUpdate(conn, member);
			
			if(result > 0)  commit(conn);
			else 			rollback(conn);
			
			close(conn);
			
			return result;
		
	}

	/** 회원 탈퇴 Service
	 * @param currentPwd
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberNo);
		
		if(result > 0)  commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 닉네임 중복 검사 Service
	 * @param nickname
	 * @return result
	 * @throws Exception
	 */
	public int nickDupCheck(String nickname) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.nickDupCheck(conn, nickname);
		
		close(conn);
		
		return result;
	}


}
