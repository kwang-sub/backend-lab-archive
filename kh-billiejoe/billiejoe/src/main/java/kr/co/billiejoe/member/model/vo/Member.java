package kr.co.billiejoe.member.model.vo;

import java.sql.Timestamp;

public class Member {
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private String memberPic;
	private Timestamp regDate;
	private Timestamp unRegDate;
	private String memberStatus;
	private String memberIsAdmin;
	
	public Member() {}

	public Member(int memberNo, String memberEmail, String memberName, String memberPhone,
			String memberPic, Timestamp regDate, String memberStatus, String memberIsAdmin) {
		this.memberNo = memberNo;
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberPic = memberPic;
		this.regDate = regDate;
		this.memberStatus = memberStatus;
		this.memberIsAdmin = memberIsAdmin;
	}
	
	
	
	public Member(String memberEmail, String memberName) {
		super();
		this.memberEmail = memberEmail;
		this.memberName = memberName;
	}

	public Member(int memberNo, String memberEmail, String memberPw, String memberName, String memberPhone,
			String memberPic, Timestamp regDate, String memberStatus, String memberIsAdmin) {
		this.memberNo = memberNo;
		this.memberEmail = memberEmail;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberPic = memberPic;
		this.regDate = regDate;
		this.memberStatus = memberStatus;
		this.memberIsAdmin = memberIsAdmin;
	}
	
	

	public Timestamp getUnRegDate() {
		return unRegDate;
	}

	public void setUnRegDate(Timestamp unRegDate) {
		this.unRegDate = unRegDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberPic() {
		return memberPic;
	}

	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberIsAdmin() {
		return memberIsAdmin;
	}

	public void setMemberIsAdmin(String memberIsAdmin) {
		this.memberIsAdmin = memberIsAdmin;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberEmail=" + memberEmail + ", memberPw=" + memberPw
				+ ", memberName=" + memberName + ", memberPhone=" + memberPhone + ", memberPic=" + memberPic
				+ ", regDate=" + regDate + ", memberStatus=" + memberStatus + ", memberIsAdmin=" + memberIsAdmin + "]";
	}
	
}
