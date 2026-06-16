package kr.co.billiejoe.chat.model.vo;

public class ChatUser {
	private int memberNo;
	private int chatRoomNo;
	private String joinStatus;
	
	public ChatUser() {}
	
	public ChatUser(int memberNo, int chatRoomNo, String joinStatus) {
		this.memberNo = memberNo;
		this.chatRoomNo = chatRoomNo;
		this.joinStatus = joinStatus;
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
	
	public String getJoinStatus() {
		return joinStatus;
	}

	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
	}

	@Override
	public String toString() {
		return "ChatUser [memberNo=" + memberNo + ", chatRoomNo=" + chatRoomNo + "]";
	}
	
	
}
