package edu.kh.yeowoori.member.model.service;

import static edu.kh.yeowoori.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.yeowoori.member.model.dao.myPageDAO;
import edu.kh.yeowoori.member.model.vo.Member;
public class myPageService {
	myPageDAO dao = new myPageDAO();
	/**내가 작성한 여행글 가져오기
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public List<Member> getMyTrip(Member loginMember) throws Exception{
		Connection conn = getConnection();
		List<Member> myTrip = dao.getMyTrip(conn, loginMember);
		close(conn);
		return myTrip;
		
	}
	/**내가 작성한 질문글 가져오기
	 * @param loginMember
	 * @return
	 */
	public List<Member> getMyQuestion(Member loginMember) throws Exception {
		Connection conn = getConnection();
		List<Member> myQuestion = dao.getMyQuestion(conn, loginMember);
		close(conn);		
		return myQuestion;
	}
	/**내가 작성한 같이떠나요 가져오기
	 * @param loginMember
	 * @return
	 */
	public List<Member> getMyTogether(Member loginMember) throws Exception {
		Connection conn = getConnection();
		List<Member> myTogether = dao.getMyTogether(conn, loginMember);
		close(conn);
		return myTogether;
	}
	/**내가 받은 좋아요 수 가져오기
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public int getLikeCount(Member loginMember) throws Exception {
		Connection conn = getConnection();
		Member boardLike = dao.getBoardLikeCount(conn, loginMember);
		Member commentLike = dao.getCommentLikeCount( loginMember,conn);
		
		
		
		
		int likeCount = boardLike.getCount()+commentLike.getCount();
		close(conn);
		
		
		return likeCount;
	}

	public List<Member> getLikeTrip(Member loginMember) throws Exception{
		Connection conn = getConnection();
		List<Member> myTrip = dao.getLikeTrip(conn, loginMember);
		close(conn);
		return myTrip;
	}
	/**내가 작성한 질문글 가져오기
	 * @param loginMember
	 * @return
	 */
	public List<Member> getLikeQuestion(Member loginMember) throws Exception {
		Connection conn = getConnection();
		List<Member> myQuestion = dao.getLikeQuestion(conn, loginMember);
		close(conn);		
		return myQuestion;
	}
	/**내가 작성한 같이떠나요 가져오기
	 * @param loginMember
	 * @return
	 */
	public List<Member> getLikeTogether(Member loginMember) throws Exception {
		Connection conn = getConnection();
		List<Member> myTogether = dao.getLikeTogether(conn, loginMember);
		close(conn);
		return myTogether;
	}
}
