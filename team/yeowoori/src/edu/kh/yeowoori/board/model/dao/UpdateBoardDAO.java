package edu.kh.yeowoori.board.model.dao;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import edu.kh.yeowoori.board.model.vo.Attachment;
import edu.kh.yeowoori.board.model.vo.Board;

import static edu.kh.yeowoori.common.JDBCTemplate.*;

public class UpdateBoardDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;
	
	public UpdateBoardDAO() {
		String filePath = SelectBoardDAO.class.getResource("/edu/kh/yeowoori/sql/board/updateBoard-query.xml").getPath();
		try {
			prop = new Properties();
//			filePath 변수에 저장된 경로로 부터 Xml 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 게시글 수정 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Board board) throws Exception {
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getAreaCode());
			pstmt.setInt(4, board.getCategoryCode());
			pstmt.setInt(5, board.getBoardNo());
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * 이미지 수정 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int updateAttachment(Connection conn, Attachment at) throws Exception{
		int result = 0;
		String sql = prop.getProperty("updateAttachment");
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, at.getFileNm());
			pstmt.setInt(2, at.getBoardNo());
			pstmt.setInt(3, at.getFileLevel());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/**첨부파일 삽입 DAO
	 * @param conn
	 * @param at
	 * @return
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception{
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			System.out.println(at);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFilePath());
			pstmt.setString(2, at.getFileNm());
			pstmt.setInt(3,at.getFileLevel());
			pstmt.setInt(4, at.getBoardNo());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int boardNo)throws Exception {
		int result = 0 ;
		String sql = prop.getProperty("deleteBoard");
		try{
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateLike(Connection conn, int boardNo, int memberNo) throws Exception{
		int result = 0;
		String sql =prop.getProperty("updateLike");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberNo);
			pstmt.setInt(2,boardNo);
		
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int checkLike(Connection conn, int boardNo, int memberNo) throws Exception {

		int result = 0;
		String sql = prop.getProperty("checkLike");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberNo);
			pstmt.setInt(2,boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int checkLikeComment(Connection conn, int commentNo, int memberNo) throws Exception{
		int result = 0;
		String sql = prop.getProperty("checkLikeComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberNo);
			pstmt.setInt(2,commentNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int updateLikeComment(Connection conn, int commentNo, int memberNo) throws Exception {
		int result = 0;
		String sql =prop.getProperty("updateLikeComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,commentNo);
			pstmt.setInt(2,memberNo);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}




}
