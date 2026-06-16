package edu.kh.yeowoori.board.model.service;

import static edu.kh.yeowoori.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.yeowoori.board.model.dao.HomeBoardDAO;
import edu.kh.yeowoori.board.model.vo.Board;
import edu.kh.yeowoori.board.model.vo.Notice;

public class HomeBoardService {

	HomeBoardDAO dao = new HomeBoardDAO();

	/**
	 * 홈보드 게시글 목록 조회 Service
	 * @param area
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectHomeBoard(int area) throws Exception {
		
		Connection conn = getConnection();
		List<Board> boardList = dao.selectHomeBoard(area,conn);
		close(conn);
		
		return boardList;
	}

	/**
	 * 홈보드 공지글 목록 조회 Service
	 * @return noticeList
	 * @throws Exception
	 */
	public List<Notice> selectHomeNotice() throws Exception{
		Connection conn = getConnection();
		List<Notice> noticeList = dao.selectHomeNotice(conn);
		close(conn);
		return noticeList;
	}

	/**
	 * 홈보드 질문 목록 조회 Service
	 * @param area
	 * @param type 
	 * @return qBordList
	 * @throws Exception
	 */
	public List<Board> selectHomeQuestion(int area, int type) throws Exception{
		Connection conn = getConnection();
		List<Board> boardList = dao.selectHomeQuestion(conn, area, type);
		close(conn);
		return boardList;
	}
	
	/** 홈보드 전체지역 여행게시글 목록 조회 Service
	 * 
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectHomeTrip() throws Exception{
		Connection conn = getConnection();
		List<Board> boardList = dao.selectHomeTrip(conn);
		close(conn);
		return boardList;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectHomeOther(int type) throws Exception {
		Connection conn = getConnection();
		List<Board> boardList = dao.selectHomeOther(conn, type);
		close(conn);
		return boardList;
	}
	
	
}
