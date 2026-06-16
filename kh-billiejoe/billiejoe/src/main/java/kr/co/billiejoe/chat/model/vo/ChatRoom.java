package kr.co.billiejoe.chat.model.vo;

public class ChatRoom {
	private int chatRoomNo;
	private int placeNo;
	private int memberNo;
	private int placeMemberNo;
	
	public ChatRoom() {}
	
	
	
	public ChatRoom(int chatRoomNo, int placeNo, int memberNo, int placeMemberNo) {
		super();
		this.chatRoomNo = chatRoomNo;
		this.placeNo = placeNo;
		this.memberNo = memberNo;
		this.placeMemberNo = placeMemberNo;
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

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "ChatRoom [chatRoomNo=" + chatRoomNo + ", placeNo=" + placeNo + ", memberNo=" + memberNo
				+ ", placeMemberNo=" + placeMemberNo + "]";
	}

	
	
	
}
