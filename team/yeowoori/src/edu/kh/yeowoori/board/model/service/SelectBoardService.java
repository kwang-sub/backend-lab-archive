package edu.kh.yeowoori.board.model.service;

import static edu.kh.yeowoori.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.yeowoori.board.model.dao.SelectBoardDAO;
import edu.kh.yeowoori.board.model.vo.Board;
import edu.kh.yeowoori.board.model.vo.Category;
import edu.kh.yeowoori.board.model.vo.Pagination;

public class SelectBoardService {

	private SelectBoardDAO dao = new SelectBoardDAO();
	
	
	
	/** 페이징 처리 객체 생성용 service
	 * @param cp
	 * @param boardType
	 * @param category 
	 * @param area 
	 * @return pagination
	 */
	public Pagination getPagination(int cp, int boardType, int area, int category) throws Exception {
		
		
		Connection conn = getConnection();
		Map<String, Object> map = null;
		
//		DB에서 전체 게시글 수 +게시판 이름을 얻어옴
		if(area!=0 && category==0) {
			map = dao.getListCount(conn, cp, boardType, area);
		}else if(area==0 && category !=0 ) {
			map = dao.getListCount(cp, conn, boardType, category);
		}else if(area!=0 && category != 0) {
			map = dao.getListCount(cp, conn, boardType, category, area);
		}else {
			map = dao.getListCount(conn, cp, boardType);
			
		}
		
		close(conn);
		
		int listCount = (map.get("listCount")==null) ? 0 : (int)map.get("listCount");
		String boardName = (String)map.get("boardName");
		
		
		return new Pagination(cp, listCount, boardType, boardName);
	}
	
	/** 내가 작성한글 페이지네이션처리
	 * @param cp
	 * @param memberNo
	 * @param memberNo2 
	 * @return
	 * @throws Exception
	 */
	public Pagination getMyPagination(int cp, int boardType, int memberNo) throws Exception {
		Connection conn = getConnection();
		Map<String, Object> map = dao.getMyListCount(conn, cp, boardType,memberNo);
		close(conn);
		int listCount = (map.get("listCount")==null) ? 0 : (int)map.get("listCount");
		String boardName = (String)map.get("boardName");
		
		return new Pagination(cp, listCount, boardType, boardName);
	}
	/**내가 좋아요한글 페이지네이션 처리
	 * @param cp
	 * @param boardType
	 * @param memberNo
	 * @return
	 */
	public Pagination getLikePagination(int cp, int boardType, int memberNo) throws Exception {
		Connection conn = getConnection();
		Map<String, Object> map = dao.getLikeListCount(conn, cp, boardType,memberNo);
		close(conn);
		int listCount = (map.get("listCount")==null) ? 0 : (int)map.get("listCount");
		String boardName = (String)map.get("boardName");
		
		return new Pagination(cp, listCount, boardType, boardName);
	}




	/**게시글 목록 조회
	 * @param pagination
	 * @param category 
	 * @param area 
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination, int area, int category) throws Exception {
		Connection conn = getConnection();
		
		List<Board> boardList = null;
		if(area != 0 && category == 0) {
			boardList = dao.selectBoardList(conn, area, pagination);
		}else if (area == 0 && category != 0) {
			boardList = dao.selectBoardList(category, conn, pagination);
		}else if (area != 0 && category != 0) {
			boardList = dao.selectBoardList(category, conn, pagination, area);
			
		}else {
			boardList = dao.selectBoardList(conn, pagination);
		}
		
		close(conn);
		return boardList;
	}

	/**	내가 작성한 게시글 목록 조회
	 * @param pagination
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination, int memberNo) throws Exception {
		Connection conn = getConnection();
		List<Board> boardList = null;
		boardList = dao.selectBoardList(conn, pagination, memberNo);
		close(conn);
		return boardList;
	}

	/**
	 * 게시글 상세조회 Service
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		Board board = dao.selectBoard(conn, boardNo);
		if(board.getBoardTitle() !=null) {
			int result = dao.increaseReadCount(conn, boardNo);
			if(result>0) {
				commit(conn);
				board.setReadCount(board.getReadCount()+1);
			}else {
				rollback(conn);
			}
		}
		close(conn);
		
		return board;
	}

	/**  공지사항 페이지네이션
	 * @param cp
	 * @return
	 */
	public Pagination getNoticePagination(int cp) throws Exception {
		Connection conn = getConnection();
		int listCount = dao.getNoticePagination(conn);
	
		return new Pagination(cp, listCount);
	}

	/**공지사항 리스트 가져오기
	 * @param cp 
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectNoticeBoardList(int cp, Pagination pagination) throws Exception {
		Connection conn = getConnection();
		List<Board> boardList = dao.selectNoticeBoardList(pagination, conn, cp);
		return boardList;
	}

	/**
	 * 카테고리 리스트 조회
	 * @return categoryList
	 */
	public List<Category> selectCategoryList() throws Exception{

		Connection conn= getConnection();
		List<Category>  categoryList = dao.selectCategoryList(conn);
		close(conn);
		return null;
	}

	/** 내가 좋아요한 게시글 목록조회
	 * @param pagination
	 * @param memberNo
	 * @param like
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination, int memberNo, String like) throws Exception {
		Connection conn = getConnection();
		List<Board> boardList = null;
		boardList = dao.selectBoardList(conn, pagination, memberNo,like);
		close(conn);
		return boardList;
	}

	/** 검색어에 따른 페이지네이션
	 * @param cp
	 * @param boardType
	 * @param search
	 * @return
	 */
	public Pagination getPagination(int cp, int boardType, String search) throws Exception {
		Connection conn = getConnection();
		
		String condition = createCondition(search);
		
		int listCount = dao.getPagination(conn, condition, boardType);
		close(conn);
		return new Pagination(cp, listCount,boardType);
	}
	
	private String createCondition(String search) {
		String condition = " AND (BOARD_TITLE LIKE '%"+search+"%' "+
				" OR BOARD_CONTENT LIKE '%"+search+"%') ";;
		
		return condition;
		
	}

	/**검색에 따른 보드리스트 구하기
	 * @param pagination
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination, String search) throws Exception {
		Connection conn = getConnection();
		String condition = createCondition(search);
		List<Board> boardList = dao.selectSearchBoardList(conn, pagination, search, condition );
		
		return boardList;
	}

	




}
