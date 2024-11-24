package dto;

import java.util.Date;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberNick;
	private String memberPhone;
	private String memberPostcode;
	private String memberAddr1;
	private String memberAddr2;
	private Date insertDat;
	
	public Member() {}

	public Member(int memberNo, String memberId, String memberPw, String memberNick, String memberPhone,
			String memberPostcode, String memberAddr1, String memberAddr2, Date insertDat) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberNick = memberNick;
		this.memberPhone = memberPhone;
		this.memberPostcode = memberPostcode;
		this.memberAddr1 = memberAddr1;
		this.memberAddr2 = memberAddr2;
		this.insertDat = insertDat;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberNick="
				+ memberNick + ", memberPhone=" + memberPhone + ", memberPostcode=" + memberPostcode + ", memberAddr1="
				+ memberAddr1 + ", memberAddr2=" + memberAddr2 + ", insertDat=" + insertDat + "]";
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

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberPostcode() {
		return memberPostcode;
	}

	public void setMemberPostcode(String memberPostcode) {
		this.memberPostcode = memberPostcode;
	}

	public String getMemberAddr1() {
		return memberAddr1;
	}

	public void setMemberAddr1(String memberAddr1) {
		this.memberAddr1 = memberAddr1;
	}

	public String getMemberAddr2() {
		return memberAddr2;
	}

	public void setMemberAddr2(String memberAddr2) {
		this.memberAddr2 = memberAddr2;
	}

	public Date getInsertDat() {
		return insertDat;
	}

	public void setInsertDat(Date insertDat) {
		this.insertDat = insertDat;
	}

}
