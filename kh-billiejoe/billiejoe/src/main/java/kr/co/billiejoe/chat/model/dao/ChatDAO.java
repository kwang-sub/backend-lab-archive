package kr.co.billiejoe.chat.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.billiejoe.chat.model.vo.ChatList;
import kr.co.billiejoe.chat.model.vo.ChatMsg;
import kr.co.billiejoe.chat.model.vo.ChatRoom;
import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.place.model.vo.Place;

@Repository
public class ChatDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**채팅방있는지 확인
	 * @param chatRoomNo
	 * @return
	 */
	public int checkChatRoom(int chatRoomNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("chatMapper.checkChatRoom",chatRoomNo);
	}

	/**채팅방 없을시 채팅방 만들기
	 * @param chatRoom
	 * @return
	 */
	public int insertChatRoom(ChatRoom chatRoom) {
		return sqlSession.insert("chatMapper.insertChatRoom", chatRoom);
	}

	/** 채팅방 만들고 장소주인이랑 로그인멤버 채팅방에 바로 넣기
	 * @param chatRoom
	 * @return
	 */
	public int joinChatRoom(ChatRoom chatRoom) {
		return sqlSession.insert("chatMapper.joinChatRoom",chatRoom);
	}
	
	/**채팅방 내용 조회
	 * @param chatRoomNo
	 * @return
	 */
	public List<ChatMsg> selectList(int chatRoomNo) {
		return sqlSession.selectList("chatMapper.selectList", chatRoomNo);
	}

	/**채팅상세보기 장소 조회
	 * @param chatRoom
	 * @return
	 */
	public Place chatPlace(ChatRoom chatRoom) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("chatMapper.chatPlace", chatRoom);
	}

	public int insertChatMsg(ChatMsg chatMsg) {
		// TODO Auto-generated method stub
		return sqlSession.insert("chatMapper.insertChatMsg", chatMsg);
	}

	/**채팅목록조회
	 * @param memberNo
	 * @return
	 */
	public List<ChatList> selectChatList(int memberNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("chatMapper.selectChatList", memberNo);
	}
	
	/**채팅방에 채팅입력되면 채팅방 인원 전부 활성화
	 * @param chatRoomNo
	 */
	public void allOpenChat(int chatRoomNo) {
		sqlSession.update("chatMapper.allOpenChat", chatRoomNo);		
	}

	/**채팅나가기
	 * @param chatRoom
	 * @return
	 */
	public int chatRoomDelete(ChatRoom chatRoom) {
		// TODO Auto-generated method stub
		return sqlSession.update("chatMapper.chatRoomDelete", chatRoom);
	}

	/**채팅 대화상대 구하기
	 * @param chatRoom
	 * @return
	 */
	public Member chatMember(ChatRoom chatRoom) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("chatMapper.chatMember", chatRoom);
	}

	
}
