package edu.kh.yeowoori.board.model.service;
import static edu.kh.yeowoori.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import edu.kh.yeowoori.board.model.dao.BoardDAO;
import edu.kh.yeowoori.board.model.vo.Attachment;
import edu.kh.yeowoori.board.model.vo.Board;

public class BoardService {
	BoardDAO dao = new BoardDAO();
	/**게시글 삽입 service
	 * @param board
	 * @param atList
	 * @param boardType
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Board board, List<Attachment> atList, int boardType) throws Exception {
		Connection conn = getConnection();
		int boardNo = dao.nextBoardNo(conn);
		int result = 0;
		if(boardNo>0) {
			board.setBoardNo(boardNo);
			String boardContent = board.getBoardContent();
			
			replaceParameter(boardContent);
			board.setBoardTitle(replaceParameter(board.getBoardTitle()));
			boardContent = boardContent.replaceAll("\r\n", "<br>");
			board.setBoardContent(boardContent);
			
			
			result = dao.insertBoard(conn, board, boardType);
			if(result > 0) {
				for(Attachment at : atList) {
					at.setBoardNo(boardNo);
					result = dao.insertAttachment(conn, at);
					if(result == 0) {
						rollback(conn);
						break;
					}
				}
				if(result>0) {
					commit(conn);
					result = boardNo;
				}
			}else {
				rollback(conn);
			}
		}
		close(conn);
		return result;
	}
	
	
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


	/**공지사항 게시글 작성
	 * @param board
	 * @return
	 */
	public int insertNoticeBoard(Board board) throws Exception{
		Connection conn = getConnection();
		String boardContent = board.getBoardContent();
		replaceParameter(boardContent);
		board.setBoardTitle(replaceParameter(board.getBoardTitle()));
		boardContent = boardContent.replaceAll("\r\n", "<br>");
		board.setBoardContent(boardContent);
		
		int result = dao.insertNoticeBoard(board, conn);
		if(result > 0 ) commit(conn);
		else rollback(conn);
		return result;
	}

}
