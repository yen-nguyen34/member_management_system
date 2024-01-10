package com.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.dto.*;
import com.project.exception.MemberErrorResponse;
import com.project.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.entity.Member;
import com.project.repository.MemberRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @Override
    public Member addMember(MemberSaveDto memberSaveDto) {
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

    @Override
    public List<MemberDto> exportSearchResultsToExcel(MemberSearchDto memberSearchDto) {
        return convertToMemberDtoList(searchMembers(memberSearchDto));
    }

    private List<MemberDto> convertToMemberDtoList(List<Member> members) {
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member i : members) {
            MemberDto memberDto = new MemberDto(i.getMemberNo(), i.getMemberId(), i.getMemberName(), i.getMemberPhone(),
                    i.getMemberEmail(), i.getSignUpDate(), i.getUpdateDate());
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    // delete member
    @Override
    public Boolean deleteMember(long memberNo) {
        if (memberRepository.existsById(memberNo)) {
            memberRepository.deleteById(memberNo);
        } else {
            throw new MemberNotFoundException("Member Not Found for ID: " + memberNo);
        }
        return true;
    }

    @Override
    public Boolean checkDuplicateId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    @Override
    public Boolean isValidPassword(String memberPwd) {
        // At least one letter
        String regexLetter = ".*[a-zA-Z].*";

        // At least one digit
        String regexDigit = ".*\\d.*";

        // At least one special character
        String regexSpecialChar = ".*[!@#$%^&*].*";

        // 10-20 characters for one letter with one digit or one special character
        String regexLength2Combinations = "^.{10,20}$";

        // 8-20 characters for both letter, digit, and special character
        String regexLength3Combinations = "^.{8,20}$";

        // No more than 3 consecutive digits
        String regexConsecutiveDigits = "^(?!.*(?:012|123|234|345|456|567|678|789|890|987|876|765|654|543|432|321|210|0123|1234|2345|3456|4567|5678|6789|9876|8765|7654|6543|5432|4321|9012))[a-zA-Z!@#$%^&*]*(\\d{1,2}[a-zA-Z!@#$%^&*]*)*$";

        return ((memberPwd.matches(regexLetter) && memberPwd.matches(regexDigit) && !memberPwd.matches(regexSpecialChar) && memberPwd.matches(regexLength2Combinations)
                && memberPwd.matches(regexConsecutiveDigits))
                || (memberPwd.matches(regexLetter) && !memberPwd.matches(regexDigit) && memberPwd.matches(regexSpecialChar) && memberPwd.matches(regexLength2Combinations)
                && memberPwd.matches(regexConsecutiveDigits))
                || (!memberPwd.matches(regexLetter) && memberPwd.matches(regexDigit) && memberPwd.matches(regexSpecialChar) && memberPwd.matches(regexLength2Combinations)
                && memberPwd.matches(regexConsecutiveDigits))
                || (memberPwd.matches(regexLetter) && memberPwd.matches(regexDigit) &&
                memberPwd.matches(regexSpecialChar) && memberPwd.matches(regexLength3Combinations) && memberPwd.matches(regexConsecutiveDigits)));
    }

    // get Member by memberNo
    @Override
    public Member getMemberByNo(long memberNo) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new MemberNotFoundException("Member Not Found for ID: " + memberNo));

        return member;
    }

    // update member by memberNo
    @Override
    public Member updateMember(long memberNo, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new MemberNotFoundException("Member Not Found for ID: " + memberNo));
        LocalDateTime now = LocalDateTime.now();
        member.setMemberId(memberUpdateDto.getMemberId());
        member.setMemberName(memberUpdateDto.getMemberName());
        member.setMemberPwd(memberUpdateDto.getMemberPwd());
        member.setMemberPhone(memberUpdateDto.getMemberPhone());
        member.setMemberEmail(memberUpdateDto.getMemberEmail());
        member.setUpdateDate(now);
        return memberRepository.save(member);
    }

    // Log in by ID and Password
    @Override
    public LoginResponse loginResponse(String message, boolean status) {
        
        return null;
    }
}
