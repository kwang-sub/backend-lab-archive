package edu.kh.yeowoori.board.model.service;
import static edu.kh.yeowoori.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import edu.kh.yeowoori.board.model.dao.BoardDAO;
import edu.kh.yeowoori.board.model.dao.UpdateBoardDAO;
import edu.kh.yeowoori.board.model.vo.Attachment;
import edu.kh.yeowoori.board.model.vo.Board;

public class UpdateBoardService {
	
	UpdateBoardDAO dao = new UpdateBoardDAO();

	 // 크로스 사이트 스크립트 방지 메소드
	   private String replaceParameter(String param) {
	      String result = param;
	      if(param != null) {
	         result = result.replaceAll("&", "&amp;");
	         result = result.replaceAll("<", "&lt;");
	         result = result.replaceAll(">", "&gt;");
	         result = result.replaceAll("\"", "&quot;");
	      }
	      
	      return result;
	   }

	/**
	 * 게시글 수정 service
	 * @param board
	 * @param atList
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board, List<Attachment> atList) throws Exception{
		Connection conn = getConnection();
		
		board.setBoardContent(replaceParameter(board.getBoardContent()));
		board.setBoardTitle(replaceParameter(board.getBoardTitle()));
		board.setBoardContent(board.getBoardContent().replaceAll("\r\n", "<br>"));
		
		int result = dao.updateBoard(conn,board);
		
		if(result>0) {
			for(Attachment at : atList) {
				result = dao.updateAttachment(conn, at);
				if(result == 0) {
					result = dao.insertAttachment(conn, at);
					if(result == 0) {
						rollback(conn);
						break;
					
					}
				}
			}
			if(result >0) commit(conn);
			else rollback(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int deleteBoard(int boardNo) throws Exception {
		Connection conn= getConnection();
		int result = dao.deleteBoard(conn,boardNo);
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public int updateLike(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.updateLike(conn, boardNo, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int checkLike(int boardNo, int memberNo)throws Exception {
		Connection conn = getConnection();
		int result = dao.checkLike(conn, boardNo, memberNo);
		close(conn);
		return result;
	}

	public int checkLikeComment(int commentNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.checkLikeComment(conn, commentNo, memberNo);
		close(conn);
		return result;
	}

	public int updateLikeComment(int commentNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.updateLikeComment(conn, commentNo, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
}
