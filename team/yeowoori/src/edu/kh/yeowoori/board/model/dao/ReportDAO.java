package edu.kh.yeowoori.board.model.dao;

import static edu.kh.yeowoori.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
public class ReportDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;
	
	public ReportDAO() {
		String filePath = SelectBoardDAO.class.getResource("/edu/kh/yeowoori/sql/board/Report-query.xml").getPath();
		try {
			prop = new Properties();
//			filePath 변수에 저장된 경로로 부터 Xml 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**보드 신고  DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int reportBoard(Connection conn, int boardNo, int memberNo) throws Exception {
		int result = 0;
		String sql = prop.getProperty("reportBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	/**댓글 신고  DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int reportComment(Connection conn, int commentNo, int memberNo) throws Exception {
		int result = 0;
		String sql = prop.getProperty("reportComment");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, commentNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
}
