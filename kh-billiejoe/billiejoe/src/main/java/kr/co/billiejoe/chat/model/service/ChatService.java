package kr.co.billiejoe.chat.model.service;

import java.util.List;

import kr.co.billiejoe.chat.model.vo.ChatList;
import kr.co.billiejoe.chat.model.vo.ChatMsg;
import kr.co.billiejoe.chat.model.vo.ChatRoom;
import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.place.model.vo.Place;

public interface ChatService {

	int openChatRoom(ChatRoom chatRoom);

	List<ChatMsg> selectList(ChatRoom chatRoom);

	Place chatPlace(ChatRoom chatRoom);

	int insertChatMsg(ChatMsg chatMsg);

	List<ChatList> selectChatList(int memberNo);

	int chatRoomDelete(ChatRoom chatRoom);

	Member chatMember(ChatRoom chatRoom);

	

}
