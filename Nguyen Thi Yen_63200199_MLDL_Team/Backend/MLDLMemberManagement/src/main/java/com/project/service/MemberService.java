package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.dto.MemberDto;
import com.project.dto.MemberSaveDto;
import com.project.dto.MemberSearchDto;
import com.project.dto.MemberUpdateDto;
import com.project.entity.Member;

public interface MemberService {

	Member signUpMember(MemberSaveDto memberSaveDto);

	List<MemberDto> getAllMembers();

	List<Member> searchMembers(MemberSearchDto memberSearchDto);

	List<MemberDto> exportSearchResultsToExcel(MemberSearchDto memberSearchDto);
	Optional<Member> getMemberByNo(long memberNo);

	Member updateMember(MemberUpdateDto memberUpdateDto);

	Boolean deleteMember(long memberNo);

	Boolean checkDuplicateId(String memberId);

	Boolean isValidPassword(String memberPwd);

}
