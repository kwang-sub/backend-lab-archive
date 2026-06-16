package kr.co.billiejoe.chat.model.vo;


import java.sql.Timestamp;
import java.util.List;

import kr.co.billiejoe.member.model.vo.Member;

public class ChatList {
	private int chatRoomNo;
	private int memberNo;
	private int chatPlaceNo;
	private String chatFilePath;
	private String chatFileName;
	private String chatMsg;
	private Timestamp chatMsgDate;
	private Member member;//대화 상대 정보 담기용
	private int placeMemberNo;
	
	public ChatList() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getPlaceMemberNo() {
		return placeMemberNo;
	}


	public void setPlaceMemberNo(int placeMemberNo) {
		this.placeMemberNo = placeMemberNo;
	}


	public int getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}

	public int getmemberNo() {
		return memberNo;
	}

	public void setmemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getChatPlaceNo() {
		return chatPlaceNo;
	}

	public void setChatPlaceNo(int chatPlaceNo) {
		this.chatPlaceNo = chatPlaceNo;
	}

	public String getChatFilePath() {
		return chatFilePath;
	}

	public void setChatFilePath(String chatFilePath) {
		this.chatFilePath = chatFilePath;
	}

	public String getChatFileName() {
		return chatFileName;
	}

	public void setChatFileName(String chatFileName) {
		this.chatFileName = chatFileName;
	}

	public String getChatMsg() {
		return chatMsg;
	}

	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
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

	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}


	@Override
	public String toString() {
		return "ChatList [chatRoomNo=" + chatRoomNo + ", memberNo=" + memberNo + ", chatPlaceNo=" + chatPlaceNo
				+ ", chatFilePath=" + chatFilePath + ", chatFileName=" + chatFileName + ", chatMsg=" + chatMsg
				+ ", chatMsgDate=" + chatMsgDate + ", member=" + member + ", placeMemberNo=" + placeMemberNo + "]";
	}

	
	
	
	
}
