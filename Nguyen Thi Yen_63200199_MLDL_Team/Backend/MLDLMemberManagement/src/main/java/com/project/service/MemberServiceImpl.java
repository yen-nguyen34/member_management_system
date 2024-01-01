package com.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.MemberDto;
import com.project.dto.MemberSaveDto;
import com.project.dto.MemberSearchDto;
import com.project.dto.MemberUpdateDto;
import com.project.entity.Member;
import com.project.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	// sign-up
	@Override
	public Member signUpMember(MemberSaveDto memberSaveDto) {
		LocalDateTime now = LocalDateTime.now();
		Member member = new Member(memberSaveDto.getMemberId(), memberSaveDto.getMemberName(),
				memberSaveDto.getMemberPwd(), memberSaveDto.getMemberPhone(), memberSaveDto.getMemberEmail(), now, now);
		return memberRepository.save(member);
	}

	// list all members
	@Override
	public List<MemberDto> getAllMembers() {
		List<Member> member = memberRepository.findAll();
		List<MemberDto> memberDtoList = new ArrayList<>();
		for (Member i : member) {
			MemberDto memberDto = new MemberDto(i.getMemberNo(), i.getMemberId(), i.getMemberName(), i.getMemberPhone(),
					i.getMemberEmail(), i.getSignUpDate(), i.getUpdateDate());
			memberDtoList.add(memberDto);
		}

		return memberDtoList;
	}

	// search members
	@Override
	public List<Member> searchMembers(MemberSearchDto memberSearchDto) {
		return memberRepository.searchMembersbySQL(memberSearchDto.getMemberId(), memberSearchDto.getMemberName(),
				memberSearchDto.getMemberPhone(), memberSearchDto.getStartDate(), memberSearchDto.getEndDate());
	}

	// delete member
	@Override
	public Boolean deleteMember(long memberNo) {
		if (memberRepository.existsById(memberNo)) {
			memberRepository.deleteById(memberNo);
		} else {
			System.out.println("Member not found!");
		}
		return true;
	}

	// get Member by memberNo
	@Override
	public Optional<Member> getMemberByNo(long memberNo) {
		Optional<Member> member = memberRepository.findById(memberNo);
		return member;
	}

	// update member by memberNo
	@Override
	public Member updateMember(MemberUpdateDto memberUpdateDto) {
		Optional<Member> optionalMember = memberRepository.findById(memberUpdateDto.getMemberNo());

		if (optionalMember.isPresent()) {
			Member existingMember = optionalMember.get();
			existingMember.setMemberName(memberUpdateDto.getMemberName());
			existingMember.setMemberPwd(memberUpdateDto.getMemberPwd());
			existingMember.setMemberPhone(memberUpdateDto.getMemberPhone());
			existingMember.setMemberEmail(memberUpdateDto.getMemberEmail());

			return memberRepository.save(existingMember);
		} else {
			System.out.println("Member not found with memberNo: " + memberUpdateDto.getMemberNo());
			return null;
		}
	}

}
