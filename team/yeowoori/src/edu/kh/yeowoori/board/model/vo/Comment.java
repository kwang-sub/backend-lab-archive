package edu.kh.yeowoori.board.model.vo;

import java.sql.Timestamp;

public class Comment {
	private int commentNo; 		 		// 댓글 번호
	private String commentContent; 		// 댓글 내용
	private Timestamp commentDate;		// 댓글 작성일
	private int boardNo;			// 댓글이 작성된 게시글 번호
	private int memberNo;				// 댓글 작성 회원 번호
	private String memberNickname;			// 댓글 작성 회원 이름
	private String memberProfile;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public String getMemberProfile() {
		return memberProfile;
	}

	public void setMemberProfile(String memberProfile) {
		this.memberProfile = memberProfile;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentDate=" + commentDate
				+ ", boardNo=" + boardNo + ", memberNo=" + memberNo + ", memberNickname=" + memberNickname
				+ ", memberProfile=" + memberProfile + "]";
	}


	
	
	
	
	
}
