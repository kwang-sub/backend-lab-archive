package kr.co.billiejoe.chat.model.vo;

import java.sql.Timestamp;

public class ChatMsg {
	private int chatMsgNo;
	private String chatMsgContent;
	private Timestamp chatMsgDate;
	private int memberNo;
	private int chatRoomNo;
	private String memberName;
	
	public ChatMsg() {}

	public ChatMsg(int chatMsgNo, String chatMsgContent, Timestamp chatMsgDate, int memberNo, int chatRoomNo) {
		this.chatMsgNo = chatMsgNo;
		this.chatMsgContent = chatMsgContent;
		this.chatMsgDate = chatMsgDate;
		this.memberNo = memberNo;
		this.chatRoomNo = chatRoomNo;
	}
	
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getChatMsgNo() {
		return chatMsgNo;
	}

	public void setChatMsgNo(int chatMsgNo) {
		this.chatMsgNo = chatMsgNo;
	}

	public String getChatMsgContent() {
		return chatMsgContent;
	}

	public void setChatMsgContent(String chatMsgContent) {
		this.chatMsgContent = chatMsgContent;
	}

	public Timestamp getChatMsgDate() {
		return chatMsgDate;
	}

	public void setChatMsgDate(Timestamp chatMsgDate) {
		this.chatMsgDate = chatMsgDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}

	@Override
	public String toString() {
		return "ChatMsg [chatMsgNo=" + chatMsgNo + ", chatMsgContent=" + chatMsgContent + ", chatMsgDate=" + chatMsgDate
				+ ", memberNo=" + memberNo + ", chatRoomNo=" + chatRoomNo + ", memberName=" + memberName + "]";
	}

	
	
	
	
}
