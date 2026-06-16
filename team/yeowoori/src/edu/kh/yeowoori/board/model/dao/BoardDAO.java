package edu.kh.yeowoori.board.model.dao;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.yeowoori.board.model.vo.Attachment;
import edu.kh.yeowoori.board.model.vo.Board;

import static edu.kh.yeowoori.common.JDBCTemplate.*;

public class BoardDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;
	
	public BoardDAO() {
		String filePath = SelectBoardDAO.class.getResource("/edu/kh/yeowoori/sql/board/Board-query.xml").getPath();
		try {
			prop = new Properties();
//			filePath 변수에 저장된 경로로 부터 Xml 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**다음글 번호 찾기 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception {
		int boardNo = 0; 
		String sql = prop.getProperty("nextBoardNo");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				boardNo = rs.getInt(1);
				
			}
		}finally {
			close(rs);
			close(stmt);
		}
		
		return boardNo;
	}

	/**게시글 삽입 DAO
	 * @param conn
	 * @param board
	 * @param boardType
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Board board, int boardType) throws Exception {
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getMemberNo());
			pstmt.setInt(5, board.getCategoryCode());
			pstmt.setInt(6, boardType);
			pstmt.setInt(7, board.getAreaCode());
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

	/**공지사항 작성 DAO
	 * @param board
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int insertNoticeBoard(Board board, Connection conn) throws Exception {
		int result = 0;
		String sql = prop.getProperty("insertNoticeBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getMemberNo());
			result =  pstmt.executeUpdate();
					
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
