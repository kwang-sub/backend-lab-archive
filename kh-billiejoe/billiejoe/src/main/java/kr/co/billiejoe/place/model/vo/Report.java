package kr.co.billiejoe.place.model.vo;

import java.sql.Timestamp;

public class Report {
	private int reportNo;
	private String reportContent;
	private int memberNo;
	private String memberName;
	private int reviewNo;
	private Timestamp reportDate;
	private String reviewStatus;
	private String reviewContent;
	
	public Report() {}

	public Report(int reportNo, String reportContent, int memberNo, int reviewNo, Timestamp reportDate) {
		this.reportNo = reportNo;
		this.reportContent = reportContent;
		this.memberNo = memberNo;
		this.reviewNo = reviewNo;
		this.reportDate = reportDate;
	}
	
	
	
	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public Timestamp getReportDate() {
		return reportDate;
	}

	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportContent=" + reportContent + ", memberNo=" + memberNo
				+ ", memberName=" + memberName + ", reviewNo=" + reviewNo + ", reportDate=" + reportDate
				+ ", reviewStatus=" + reviewStatus + ", reviewContent=" + reviewContent + "]";
	}

	

	
	
	
}
