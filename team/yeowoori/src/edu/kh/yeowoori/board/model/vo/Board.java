package edu.kh.yeowoori.board.model.vo;

import java.sql.Timestamp;
import java.util.List;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String categoryName;
	private String areaCategory;
	private String memberNickname;
	private int readCount;
	private Timestamp createDate;
	private String boardContent;
	private String memberContent;
	private String memberProfile;
	private int commentCount;
	private int likeCount;
	private int categoryCode;
	private int areaCode;
	private int memberNo;

	private List<String> filePath;
	private List<String> fileName;

	private Timestamp modifyDate;
	private List<Attachment> atList;
	
	
	public Timestamp getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}


	public List<Attachment> getAtList() {
		return atList;
	}


	public void setAtList(List<Attachment> atList) {
		this.atList = atList;
	}


	public Board() {
		// TODO Auto-generated constructor stub
	}

	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getMemberProfile() {
		return memberProfile;
	}

	public void setMemberProfile(String memberProfile) {
		this.memberProfile = memberProfile;
	}

	
	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getMemberContent() {
		return memberContent;
	}

	public void setMemberContent(String memberContent) {
		this.memberContent = memberContent;
	}

	

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAreaCategory() {
		return areaCategory;
	}

	public void setAreaCategory(String areaCategory) {
		this.areaCategory = areaCategory;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", categoryName=" + categoryName
				+ ", areaCategory=" + areaCategory + ", memberNickname=" + memberNickname + ", readCount=" + readCount
				+ ", createDate=" + createDate + ", boardContent=" + boardContent + ", memberContent=" + memberContent
				+ ", memberProfile=" + memberProfile + ", commentCount=" + commentCount + ", likeCount=" + likeCount
				+ ", categoryCode=" + categoryCode + ", areaCode=" + areaCode + ", memberNo=" + memberNo + ", filePath="
				+ filePath + ", fileName=" + fileName + ", modifyDate=" + modifyDate + ", atList=" + atList + "]";
	}


	
	




	
	
}
