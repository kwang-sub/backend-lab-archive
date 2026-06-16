package kr.co.billiejoe.chat.websocket;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.billiejoe.chat.model.service.ChatService;
import kr.co.billiejoe.chat.model.vo.ChatMsg;
import kr.co.billiejoe.member.model.vo.Member;

public class ChatWebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	private ChatService service;
	
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		웹소켓 통신 시작
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		텍스트 받았을때 실행될 메소드
		JsonObject convertedObj = new Gson().fromJson(message.getPayload(), JsonObject.class);
		String memberName = convertedObj.get("memberName").toString();
		String chat = convertedObj.get("chat").toString();
		
		memberName = memberName.substring(1,memberName.length()-1);
		chat = chat.substring(1,chat.length()-1);
		int  chatRoomNo = Integer.parseInt(convertedObj.get("chatRoomNo").toString().replaceAll("\"", ""));
		int  memberNo = Integer.parseInt(convertedObj.get("memberNo").toString().replaceAll("\"", ""));
		
		ChatMsg chatMsg = new ChatMsg();
		chatMsg.setChatMsgContent(chat);
		chatMsg.setChatRoomNo(chatRoomNo);
		chatMsg.setMemberNo(memberNo);
		
		int result = service.insertChatMsg(chatMsg);
		
		if(result>0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
			convertedObj.addProperty("createDate", sdf.format(new Date()));
			for(WebSocketSession s : sessions) {
				int joinChatRoomNo = (Integer)s.getAttributes().get("chatRoomNo");
				if(chatRoomNo == joinChatRoomNo) {
					s.sendMessage(new TextMessage(new Gson().toJson(convertedObj)));
				}
			}
			
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		웹소켓 통신 끝날때
		sessions.remove(session);
		
	}
	
}
