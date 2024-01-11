package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.dto.*;
import com.project.entity.Member;

public interface MemberService {

    Member signUpMember(MemberSaveDto memberSaveDto);

    Member addMember(MemberSaveDto memberSaveDto);

    List<MemberDto> getAllMembers();

    List<Member> searchMembers(MemberSearchDto memberSearchDto);

    List<MemberDto> exportSearchResultsToExcel(MemberSearchDto memberSearchDto);

    Member getMemberByNo(long memberNo);

    Member updateMember(long memberNo, MemberUpdateDto memberUpdateDto);

    Boolean deleteMember(long memberNo);

    Boolean checkDuplicateId(String memberId);

    Boolean isValidPassword(String memberPwd);

    LoginResponse loginResponse(MemberLoginDto memberLoginDto);

}
