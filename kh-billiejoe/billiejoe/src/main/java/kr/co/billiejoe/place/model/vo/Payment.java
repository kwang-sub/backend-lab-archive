package kr.co.billiejoe.place.model.vo;

import java.sql.Timestamp;

public class Payment {
	private int payNo; // 결제 순번
	private int reserveNo; // 결제가 일어난 예약건 번호
	private int payAmount; // 결제 금액
	private String payMethod; // 결제 방식
	private Timestamp payDate; // 결제일시
	private int memberNo; // 구매자
	private String merchantUid; // iamport 요구값
	
	public Payment() {}

	public Payment(int payNo, int reserveNo, int payAmount, String payMethod, Timestamp payDate, int memberNo,
			String merchantUid) {
		this.payNo = payNo;
		this.reserveNo = reserveNo;
		this.payAmount = payAmount;
		this.payMethod = payMethod;
		this.payDate = payDate;
		this.memberNo = memberNo;
		this.merchantUid = merchantUid;
	}

	public int getPayNo() {
		return payNo;
	}

	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}

	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Timestamp getPayDate() {
		return payDate;
	}

	public void setPayDate(Timestamp payDate) {
		this.payDate = payDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMerchantUid() {
		return merchantUid;
	}

	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}

	@Override
	public String toString() {
		return "Payment [payNo=" + payNo + ", reserveNo=" + reserveNo + ", payAmount=" + payAmount + ", payMethod="
				+ payMethod + ", payDate=" + payDate + ", memberNo=" + memberNo + ", merchantUid=" + merchantUid + "]";
	}
	
	
}
