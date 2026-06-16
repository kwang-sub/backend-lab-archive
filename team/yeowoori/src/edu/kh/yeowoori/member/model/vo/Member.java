package edu.kh.yeowoori.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;         
    private String memberId;     
    private String memberPw;     
    private String memberEmail;
    private String memberContent;
    private String memberNickname;
    private Date enrollDate;   
    private String memberStatus;   
    private String memberGrade;
    private String memberProfile;
    
    
    private String filePath;
    private String fileNm;
    private String boardTitle;
    private int count;
    private int boardNo;
	    
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}

	
	public Member(String memberId, String memberPw, String memberEmail, String memberNickname) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
		this.memberNickname = memberNickname;
	}

	public Member(int memberNo, String memberId, String memberEmail, String memberContent,
			String memberNickname, Date enrollDate, String memberStatus, String memberGrade, String memberProfile) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		this.memberContent = memberContent;
		this.memberNickname = memberNickname;
		this.enrollDate = enrollDate;
		this.memberStatus = memberStatus;
		this.memberGrade = memberGrade;
		this.memberProfile = memberProfile;
	}
	


	public Member(int memberNo,String memberNickname, String memberProfile, String memberContent ) {
		super();
		this.memberNo = memberNo;
		this.memberNickname = memberNickname;
		this.memberProfile = memberProfile;
		this.memberContent = memberContent;
	}

	

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}


	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberContent() {
		return memberContent;
	}

	public void setMemberContent(String memberContent) {
		this.memberContent = memberContent;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	
	public String getMemberProfile() {
		return memberProfile;
	}

	public void setMemberProfile(String memberProfile) {
		this.memberProfile = memberProfile;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberEmail="
				+ memberEmail + ", memberContent=" + memberContent + ", memberNickname=" + memberNickname
				+ ", enrollDate=" + enrollDate + ", memberStatus=" + memberStatus + ", memberGrade=" + memberGrade
				+ ", memberProfile=" + memberProfile + ", filePath=" + filePath + ", fileNm=" + fileNm + ", boardTitle="
				+ boardTitle + "]";
	}


	
	
	
	
	
	
}
