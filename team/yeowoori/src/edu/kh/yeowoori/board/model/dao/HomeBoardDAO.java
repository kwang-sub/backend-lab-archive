package edu.kh.yeowoori.board.model.dao;
import static edu.kh.yeowoori.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import edu.kh.yeowoori.board.model.vo.Board;
import edu.kh.yeowoori.board.model.vo.Notice;

public class HomeBoardDAO {

	//자주 사용하는 JDBC 객체 참조 변수 선언
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//외부 xml 파일에 작성된 SQL 구문 읽어와 저장할 Properties 객체 참조 변수 선언
	private Properties prop = null;
	
	//기본 생성자를 이용한 DAO 객체 생성시 외부 xml 파일을 읽어와 prop에 저장
	public HomeBoardDAO() {
		
		//member-query.xml 파일의 경로 얻어오기
		String filePath = SelectBoardDAO.class.getResource("/edu/kh/yeowoori/sql/board/selectHomeBoard-query.xml").getPath();
		
		try {
			prop = new Properties();
			
			//filePath 변수에 저장된 경로로부터 xml파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 *  홈보드 게시글 목록 조회 DAO
	 * @param area
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectHomeBoard(int area, Connection conn) throws Exception {

		List<Board> boardList = new ArrayList<Board>();
		String sql = prop.getProperty("selectHomeBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, area);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				
				List<String> filePath = new ArrayList<String>();
				List<String> fileName = new ArrayList<String>();
				
				filePath.add(rs.getString("FILE_PATH"));
				fileName.add(rs.getString("FILE_NM"));
				
				board.setFilePath(filePath);
				board.setFileName(fileName);
				
				board.setAreaCode(area);
				
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	/**
	 *  홈보드 공지글 목록 조회 DAO
	 * @param conn
	 * @return noticeList
	 * @throws Exception
	 */
	public List<Notice> selectHomeNotice(Connection conn) throws Exception {
		List<Notice> noticeList = new ArrayList<Notice>();
		String sql = prop.getProperty("selectHomeNotice");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Notice n = new Notice();
				n.setNoticeTitle(rs.getString(1));
				n.setNoticeContent(rs.getString(2));
				n.setNoticeNo(rs.getInt(3));
				noticeList.add(n);
			}
		}finally {
			close(rs);
			close(stmt);
		}
		return noticeList;
	}

	/**
	 * 홈보드 질문 목록 조회 DAO
	 * @param conn
	 * @param area
	 * @param type 
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectHomeQuestion(Connection conn, int area, int type)throws Exception{
		List<Board> boardList = new ArrayList<Board>();
		String sql = prop.getProperty("selectHomeQuestion");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, area);
			pstmt.setInt(2, type);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setBoardNo(rs.getInt(1));
				b.setBoardTitle(rs.getString(2));
				b.setAreaCode(area);
				boardList.add(b);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	/**
	 * 홈보드 전체지역 여행게시글 목록 조회 DAO
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectHomeTrip(Connection conn) throws Exception{
		List<Board> boardList = new ArrayList<Board>();
		String sql = prop.getProperty("selectHomeTrip");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				
				List<String> filePath = new ArrayList<String>();
				List<String> fileName = new ArrayList<String>();
				
				filePath.add(rs.getString("FILE_PATH"));
				fileName.add(rs.getString("FILE_NM"));
				
				board.setFilePath(filePath);
				board.setFileName(fileName);
				
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(stmt);
		}
		return boardList;
	}

	public List<Board> selectHomeOther(Connection conn, int type) throws Exception{
		List<Board> boardList = new ArrayList<Board>();
		String sql = prop.getProperty("selectHomeOther");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setBoardNo(rs.getInt(1));
				b.setBoardTitle(rs.getString(2));
				boardList.add(b);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;

	}
	
	
	
	
}
