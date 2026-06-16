package edu.kh.yeowoori.board.model.vo;

import java.util.List;

public class Attachment {
	
	private int fileNo;
	private String filePath;
	private	String fileNm;
	private int fileLevel;
	private int boardNo;
	
	public Attachment() {
		// TODO Auto-generated constructor stub
	}
	public Attachment(int fileNo, String filePath, String fileNm, int fileLevel, int boardNo) {
		super();
		this.fileNo = fileNo;
		this.filePath = filePath;
		this.fileNm = fileNm;
		this.fileLevel = fileLevel;
		this.boardNo = boardNo;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", filePath=" + filePath + ", fileNm=" + fileNm + ", fileLevel="
				+ fileLevel + ", boardNo=" + boardNo + "]";
	}
	
	
	

}
