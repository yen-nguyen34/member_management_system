package com.project.dto;


public class MemberSearchDto {
	private String memberId = null;
	private String memberName = null;
	private String memberPhone = null;
	private String startDate = null;
	private String endDate = null;
	
	public MemberSearchDto() {
		
	}

	public MemberSearchDto(String memberId, String memberName, String memberPhone, String startDate, String endDate) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getMemberId() {
		if (this.memberId != null) {
			return "%".concat(this.memberId).concat("%");
		}
		return memberId;
	}

	public void setMemberId(String memberId) {
		if (memberId.trim().length() > 0) {
			this.memberId = memberId;
		}
	}

	public String getMemberName() {
		if (this.memberName != null) {
			return "%".concat(this.memberName).concat("%");
		}
		return memberName;
	}

	public void setMemberName(String memberName) {
		if (memberName.trim().length() > 0) {
			this.memberName = memberName;
		}
	}

	public String getMemberPhone() {
		if (this.memberPhone != null) {
			return "%".concat(this.memberPhone).concat("%");
		}
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		if (memberPhone.trim().length() > 0) {
			this.memberPhone = memberPhone;
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		if (startDate.trim().length() != 0) {
			this.startDate = startDate;
		}
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if (endDate.trim().length() != 0) {
			this.endDate = endDate;
		}
	}
}
