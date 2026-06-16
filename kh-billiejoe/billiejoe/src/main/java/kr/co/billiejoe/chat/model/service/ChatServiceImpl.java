package kr.co.billiejoe.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.billiejoe.chat.model.dao.ChatDAO;
import kr.co.billiejoe.chat.model.vo.ChatList;
import kr.co.billiejoe.chat.model.vo.ChatMsg;
import kr.co.billiejoe.chat.model.vo.ChatRoom;
import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.place.model.vo.Place;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ChatDAO dao;

	/**채팅방 만들기
	 *
	 */
	@Override
	public int openChatRoom(ChatRoom chatRoom) {
		
//		채팅방있는지 확인
		int check = dao.checkChatRoom(chatRoom.getChatRoomNo());
		
		if(check ==0) {
//			채팅방 없을시 채팅방 만들기
			check = dao.insertChatRoom(chatRoom);
			if(check >0) {
//				채팅방 만들고 장소주인이랑 로그인멤버 채팅방에 바로 넣기
				check = dao.joinChatRoom(chatRoom);
			}
		}else {//있을시
			check = 2;
		}
		
		return check;
	}

	@Override
	public List<ChatMsg> selectList(ChatRoom chatRoom) {
		return dao.selectList(chatRoom.getChatRoomNo());
	}

	/**상세보기에서 장소 정보 가져오기
	 *
	 */
	@Override
	public Place chatPlace(ChatRoom chatRoom) {
		// TODO Auto-generated method stub
		return dao.chatPlace(chatRoom);
	}

	/**채팅내용삽입하기
	 *
	 */
	@Override
	public int insertChatMsg(ChatMsg chatMsg) {
		//채팅방에 채팅입력되면 채팅방 인원 전부 활성화
		chatMsg.setChatMsgContent(replaceParameter(chatMsg.getChatMsgContent()));
		chatMsg.setChatMsgContent(chatMsg.getChatMsgContent().replace("\\n", "<br>"));
		dao.allOpenChat(chatMsg.getChatRoomNo());
		return dao.insertChatMsg(chatMsg);
	}

	/**채팅목록 조회
	 *
	 */
	@Override
	public List<ChatList> selectChatList(int memberNo) {
		// TODO Auto-generated method stub
		return dao.selectChatList(memberNo);
	}

	/**채팅나가기
	 *
	 */
	@Override
	public int chatRoomDelete(ChatRoom chatRoom) {
		// TODO Auto-generated method stub
		return dao.chatRoomDelete(chatRoom);
	}
	
	
	// 크로스 사이트 스크립트 방지 처리 메소드
		public static String replaceParameter(String param) {
			String result = param;
			if (param != null) {
				result = result.replaceAll("&", "&amp;");
				result = result.replaceAll("<", "&lt;");
				result = result.replaceAll(">", "&gt;");
				result = result.replaceAll("\"", "&quot;");
			}

			return result;
		}

		/**채팅 대화상대 프로필구하기
		 *
		 */
		@Override
		public Member chatMember(ChatRoom chatRoom) {
			// TODO Auto-generated method stub
			return dao.chatMember(chatRoom);
		}
	
}
