package com.project.dto;

import java.time.LocalDateTime;

public class MemberSaveDto {
	private String memberId;

	private String memberName;

	private String memberPwd;

	private String memberPhone;

	private String memberEmail;

	private LocalDateTime signUpDate;

	private LocalDateTime updateDate;

	public MemberSaveDto() {

	}

	public MemberSaveDto(String memberId, String memberName, String memberPwd, String memberPhone, String memberEmail,
			LocalDateTime signUpDate, LocalDateTime updateDate) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPwd = memberPwd;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.signUpDate = signUpDate;
		this.updateDate = updateDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public LocalDateTime getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(LocalDateTime signUpDate) {
		this.signUpDate = signUpDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
}
