package edu.kh.hw.model.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Member {
	private String memberId;
	private String memberPwd;
	private String name;
	private int point;		 // 충전한 포인트
	private List<Set<Integer>> purchaseLotto = new ArrayList<Set<Integer>>(); // 구매한 로또
	
	public Member() {
		super();
	}

	public Member(String memberId, String memberPwd, String name) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.name = name;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public List<Set<Integer>> getPurchaseLotto() {
		return purchaseLotto;
	}

	public void setPurchaseLotto(List<Set<Integer>> purchaseLotto) {
		this.purchaseLotto = purchaseLotto;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", name=" + name + ", point=" + point
				+ ", purchaseLotto=" + purchaseLotto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((memberPwd == null) ? 0 : memberPwd.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + point;
		result = prime * result + ((purchaseLotto == null) ? 0 : purchaseLotto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Member))
			return false;
		Member other = (Member) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (memberPwd == null) {
			if (other.memberPwd != null)
				return false;
		} else if (!memberPwd.equals(other.memberPwd))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (point != other.point)
			return false;
		if (purchaseLotto == null) {
			if (other.purchaseLotto != null)
				return false;
		} else if (!purchaseLotto.equals(other.purchaseLotto))
			return false;
		return true;
	}
	
	
	
	
	
}
