package com.project.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberUpdateDto {

    private long memberNo;

    private String memberId;

    private String memberName;

    private String memberPwd;

    private String memberPhone;

    private String memberEmail;

    private LocalDateTime updateDate;

    public MemberUpdateDto() {

    }

    public MemberUpdateDto(String memberId, String memberName, String memberPwd, String memberPhone, String memberEmail, LocalDateTime updateDate) {
        super();

        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPwd = memberPwd;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.updateDate = updateDate;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
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
