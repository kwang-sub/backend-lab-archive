package edu.kh.yeowoori.board.model.service;

import static edu.kh.yeowoori.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.yeowoori.board.model.dao.ReportDAO;

public class ReportService {
	
	ReportDAO dao = new ReportDAO();
	/**보드 신고 Service
	 * @param boardNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int reportBoard(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.reportBoard(conn, boardNo, memberNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
	}
	public int reportComment(int commentNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.reportComment(conn, commentNo, memberNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
	}

}
