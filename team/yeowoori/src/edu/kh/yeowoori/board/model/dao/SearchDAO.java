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
import edu.kh.yeowoori.board.model.vo.Pagination;

public class SearchDAO {
	
		private Statement stmt = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		private Properties prop = null;
		
		public SearchDAO() {
			
			String filePath = SelectBoardDAO.class.getResource("/edu/kh/yeowoori/sql/board/searchBoard-query.xml").getPath();
			
			try {
				prop = new Properties();
				prop.loadFromXML(new FileInputStream(filePath));
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	/**
	 * 전체 검색 DAO
	 * @param conn
	 * @param type
	 * @param maxBoardNum
	 * @param search
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(Connection conn, int type, int maxBoardNum, String search) throws Exception {
		
		List<Board> boardList = new ArrayList<Board>();
		String sql = prop.getProperty("searchBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			pstmt.setString(4, search);
			pstmt.setString(5, search);
			pstmt.setInt(6, maxBoardNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setAreaCategory(rs.getString("AREA_CATEGORY_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getTimestamp("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberContent(rs.getString("MEMBER_CONTENT"));
				board.setMemberProfile(rs.getString("MEMBER_PROFILE"));
				board.setCommentCount(rs.getInt("COMMENT_COUNT"));
				board.setLikeCount(rs.getInt("LIKE_COUNT"));
				
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
			close(pstmt);
		}
		return boardList;
	}

	/**
	 * 지역 검색 DAO
	 * @param conn
	 * @param area
	 * @param type
	 * @param maxBoardNum
	 * @param search
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchAreaBoard(Connection conn, int area, int type, int maxBoardNum, String search) throws Exception {
		
		List<Board> boardList = new ArrayList<Board>();
		String sql = prop.getProperty("searchAreaBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, area);
			pstmt.setString(3, search);
			pstmt.setString(4, search);
			pstmt.setString(5, search);
			pstmt.setString(6, search);
			pstmt.setInt(7, maxBoardNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setAreaCategory(rs.getString("AREA_CATEGORY_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getTimestamp("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberContent(rs.getString("MEMBER_CONTENT"));
				board.setMemberProfile(rs.getString("MEMBER_PROFILE"));
				board.setCommentCount(rs.getInt("COMMENT_COUNT"));
				board.setLikeCount(rs.getInt("LIKE_COUNT"));
				
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
			close(pstmt);
		}
		return boardList;
	}

	/**
	 * 게시판 검색 (지역 없음)
	 * @param conn
	 * @param pagination
	 * @param area
	 * @param boardtype
	 * @param search
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(Connection conn, Pagination pagination, String search) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		String sql = prop.getProperty("searchBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pagination.getBoardType());
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			pstmt.setString(4, search);
			pstmt.setString(5, search);
			
			int startRow = (pagination.getCurrentPage()-1)*pagination.getLimit()+1;
			int endRow = startRow + pagination.getLimit() -1;
			pstmt.setInt(6, startRow);
			pstmt.setInt(7, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setAreaCategory(rs.getString("AREA_CATEGORY_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getTimestamp("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberContent(rs.getString("MEMBER_CONTENT"));
				board.setMemberProfile(rs.getString("MEMBER_PROFILE"));
				board.setCommentCount(rs.getInt("COMMENT_COUNT"));
				board.setLikeCount(rs.getInt("LIKE_COUNT"));
				
				
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
			close(pstmt);
		}
		
		
		return boardList;
	}

	
	/**
	 * 게시판 검색 (지역 없음)
	 * @param conn
	 * @param pagination
	 * @param area
	 * @param boardtype
	 * @param search
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(Connection conn, Pagination pagination, int area, String search) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		String sql = prop.getProperty("areaSearchBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pagination.getBoardType());
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			pstmt.setString(4, search);
			pstmt.setString(5, search);
			pstmt.setInt(6, area);
			int startRow = (pagination.getCurrentPage()-1)*pagination.getLimit()+1;
			int endRow = startRow + pagination.getLimit() -1;
			pstmt.setInt(7, startRow);
			pstmt.setInt(8, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setAreaCategory(rs.getString("AREA_CATEGORY_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getTimestamp("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberContent(rs.getString("MEMBER_CONTENT"));
				board.setMemberProfile(rs.getString("MEMBER_PROFILE"));
				board.setCommentCount(rs.getInt("COMMENT_COUNT"));
				board.setLikeCount(rs.getInt("LIKE_COUNT"));
				
				
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
			close(pstmt);
		}
		
		
		return boardList;
	}

}
