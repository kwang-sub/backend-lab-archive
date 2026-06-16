package edu.kh.yeowoori.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.yeowoori.member.model.vo.Member;

import static edu.kh.yeowoori.common.JDBCTemplate.*;

public class myPageDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	public myPageDAO() {
		String filePath = MemberDAO.class.getResource("/edu/kh/yeowoori/sql/member/myPage-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**내가작성한게시글 사진 vo
	 * @param conn
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public List<Member> getMyTrip(Connection conn, Member loginMember) throws Exception {
		List<Member> myTrip = new ArrayList<Member>();
		String sql = prop.getProperty("getMyTrip");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,loginMember.getMemberNo());
			pstmt.setInt(2, loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setFilePath(rs.getString("FILE_PATH"));
				mem.setFileNm(rs.getString("FILE_NM"));
				mem.setCount(rs.getInt("COUNT"));
				mem.setBoardNo(rs.getInt("BOARD_NO"));
				myTrip.add(mem);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return myTrip;
	}
	/**내가 작성한 질문 게시글 가져오기
	 * @param conn
	 * @param loginMember
	 * @return
	 */
	public List<Member> getMyQuestion(Connection conn, Member loginMember) throws Exception {
		List<Member> myQuestion = new ArrayList<Member>();
		String sql = prop.getProperty("getMyQuestion");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setBoardTitle(rs.getString("BOARD_TITLE"));
				mem.setCount(rs.getInt("COUNT"));
				mem.setBoardNo(rs.getInt("BOARD_NO"));
				myQuestion.add(mem);
			}
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return myQuestion;
	}
	/** 내가 작성한 같이떠나요 게시글 가져오기
	 * @param conn
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public List<Member> getMyTogether(Connection conn, Member loginMember) throws Exception {
		String sql = prop.getProperty("getMyTogether");
		List<Member> myTogether = new ArrayList<Member>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setBoardTitle(rs.getString("BOARD_TITLE"));
				mem.setCount(rs.getInt("COUNT"));
				mem.setBoardNo(rs.getInt("BOARD_NO"));
				myTogether.add(mem);
			}	
		}finally {
			
			close(rs);
			close(pstmt);
		}
		return myTogether;
	}
	/**내가 작성한 게시글 좋아요 수가져오기
	 * @param conn
	 * @param loginMember
	 * @return
	 */
	public Member getBoardLikeCount(Connection conn, Member loginMember) throws Exception {
		Member boardLike = new Member();
		String sql = prop.getProperty("getBoardLikeCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardLike.setCount(rs.getInt("BOARD_LIKE"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardLike;
	}
	public Member getCommentLikeCount(Member loginMember, Connection conn) throws Exception {
		Member commentLike = new Member();
		String sql = prop.getProperty("getCommentLikeCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				commentLike.setCount(rs.getInt("COMMENT_LIKE"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return commentLike;
	}
	/**좋아요한 여행 사진 vo
	 * @param conn
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public List<Member> getLikeTrip(Connection conn, Member loginMember) throws Exception {
		List<Member> myTrip = new ArrayList<Member>();
		String sql = prop.getProperty("getLikeTrip");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,loginMember.getMemberNo());
			pstmt.setInt(2,loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setFilePath(rs.getString("FILE_PATH"));
				mem.setFileNm(rs.getString("FILE_NM"));
				mem.setCount(rs.getInt("COUNT"));
				myTrip.add(mem);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return myTrip;
	}
	/**좋아요한 질문 게시글 가져오기
	 * @param conn
	 * @param loginMember
	 * @return
	 */
	public List<Member> getLikeQuestion(Connection conn, Member loginMember) throws Exception {
		List<Member> myQuestion = new ArrayList<Member>();
		String sql = prop.getProperty("getLikeQuestion");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setBoardTitle(rs.getString("BOARD_TITLE"));
				mem.setCount(rs.getInt("COUNT"));
				myQuestion.add(mem);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return myQuestion;
	}
	/**좋아요한 같이떠나요 게시글 가져오기
	 * @param conn
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public List<Member> getLikeTogether(Connection conn, Member loginMember) throws Exception {
		String sql = prop.getProperty("getLikeTogether");
		List<Member> myTogether = new ArrayList<Member>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, loginMember.getMemberNo());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setBoardTitle(rs.getString("BOARD_TITLE"));
				mem.setCount(rs.getInt("COUNT"));
				myTogether.add(mem);
			}	
		}finally {
			
			close(rs);
			close(pstmt);
		}
		return myTogether;
	}
}
