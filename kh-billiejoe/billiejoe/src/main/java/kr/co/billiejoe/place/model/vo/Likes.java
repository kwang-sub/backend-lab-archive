package kr.co.billiejoe.place.model.vo;

import java.sql.Timestamp;

public class Likes {
	private int memberNo;
	private int placeNo;
	private Timestamp likedDate;
	
	public Likes() {}

	public Likes(int memberNo, int placeNo, Timestamp likedDate) {
		this.memberNo = memberNo;
		this.placeNo = placeNo;
		this.likedDate = likedDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public Timestamp getLikedDate() {
		return likedDate;
	}

	public void setLikedDate(Timestamp likedDate) {
		this.likedDate = likedDate;
	}

	@Override
	public String toString() {
		return "Likes [memberNo=" + memberNo + ", placeNo=" + placeNo + ", likedDate=" + likedDate + "]";
	}
	
	
}
