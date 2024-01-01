package com.project.dto;

import java.time.LocalDateTime;

public class MemberUpdateDto {
	
	private long memberNo;
	
	private String memberName;
	
	private String memberPwd;
	
	private String memberPhone;
	
	private String memberEmail;
	
	private LocalDateTime updateDate;
	
	public MemberUpdateDto() {
		
	}
	
	public MemberUpdateDto(String memberName, String memberPwd, String memberPhone, String memberEmail, LocalDateTime updateDate) {
		super();
	
		this.memberName = memberName;
		this.memberPwd = memberPwd;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.updateDate = updateDate;
	}
	
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
}
