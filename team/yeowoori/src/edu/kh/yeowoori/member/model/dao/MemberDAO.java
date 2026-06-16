package edu.kh.yeowoori.member.model.dao;


import static edu.kh.yeowoori.common.JDBCTemplate.close;
import static edu.kh.yeowoori.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.yeowoori.member.model.vo.Member;


public class MemberDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	public MemberDAO() {
		String filePath = MemberDAO.class.getResource("/edu/kh/yeowoori/sql/member/member-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 로그인 DAO
	 * @param conn
	 * @param inputId
	 * @param inputPw
	 * @return loginMember
	 */
	public Member login(Connection conn, String inputId, String inputPw) throws Exception {
		Member loginMember = null;
		try {
			String sql = prop.getProperty("login");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				int memberNo = rs.getInt("MEMBER_NO");        
			    String memberId = rs.getString("MEMBER_ID");      
			    String memberEmail = rs.getString("MEMBER_EMAIL"); 
			    String memberContent = rs.getString("MEMBER_CONTENT");
			    String memberNickname = rs.getString("MEMBER_NICKNAME");
			    Date enrollDate = rs.getDate("ENROLL_DATE");  
			    String memberStatus = rs.getString("MEMBER_STATUS");   
			    String memberGrade = rs.getString("MEMBER_GRADE"); 
			    String memberProfile = rs.getString("MEMBER_PROFILE"); 
			    
			    
			    loginMember = new Member(memberNo, memberId,  memberEmail, memberContent, memberNickname, enrollDate, memberStatus, memberGrade, memberProfile);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return loginMember;
	}
	
	
	/** 비밀번호 변경 결과 DAO
	 * @param conn
	 * @param currentPwd
	 * @param newPwd1
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePwd(Connection conn, String currentPwd, String newPwd1, int memberNo) throws Exception {
int result = 0;
		
		String sql = prop.getProperty("changePwd");
		System.out.println("----------------");
		System.out.println(currentPwd);
		System.out.println(newPwd1);
		System.out.println(memberNo);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPwd1 );
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPwd);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	/** 회원 가입 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception {

		// 1) 결과 반환용 변수 선언
				int result = 0;
				
				// 2) SQL구문을 properties에서 얻어오기
				String sql = prop.getProperty("signUp");
				
				try {
					// 3) preparedStatement 객체를 생성해서 SQL 세팅
					pstmt = conn.prepareStatement(sql);
					
					// 4) 위치홀더에 알맞은 값 대입
					pstmt.setString(1, member.getMemberId());
					pstmt.setString(2, member.getMemberPw());
					pstmt.setString(3, member.getMemberEmail());
					pstmt.setString(4, member.getMemberNickname());
					
					// 5) SQL 수행 후 결과 반환 받기
					result = pstmt.executeUpdate();
				}finally {
					// 6) 사용한 JDBC 자원 반환하기
					close(pstmt);
				}
				
				// 7) 결과를 Service로 반환하기
				return result;
	}
	/** 아이디 중복 검사 DAO
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String id) throws Exception{
		int result = 0;
		
		String sql = prop.getProperty("idDupCheck");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	/** 회원정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int memberUpdate(Connection conn, Member member)throws Exception {
		int result = 0;
		
		String sql = prop.getProperty("memberUpdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberNickname());
			pstmt.setString(2, member.getMemberProfile() );
			pstmt.setString(3, member.getMemberContent() );
			pstmt.setInt(4, member.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param currentPwd
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo) throws Exception{
		int result = 0;
		
		String sql = prop.getProperty("secession");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	/** 닉네임 중복 검사 DAO
	 * @param conn
	 * @param nickname
	 * @return result
	 * @throws Exception
	 */
	public int nickDupCheck(Connection conn, String nickname) throws Exception{
		int result = 0;
		
		String sql = prop.getProperty("nickDupCheck");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
}

