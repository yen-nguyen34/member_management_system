package com.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.MemberDto;
import com.project.dto.MemberSaveDto;
import com.project.dto.MemberSearchDto;
import com.project.dto.MemberUpdateDto;
import com.project.entity.Member;
import com.project.memberExcelExporter.MemberExcelExporter;
import com.project.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MemberController {

	@Autowired
	private MemberService memberService;


	// get all members
	@GetMapping("/members")
	public List<MemberDto> findAllMembers() {
		List<MemberDto> memberDto = memberService.getAllMembers();
		return memberDto;
	}

	// sign up member
	@PostMapping("/members/sign-up")
	public Member signUpMember(@RequestBody MemberSaveDto memberSaveDto) {
		Member member = memberService.signUpMember(memberSaveDto);
		return member;
	}

	@GetMapping("/members/check-duplicate")
	public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String memberId) {
		Boolean isDuplicate = memberService.checkDuplicateId(memberId);
		return ResponseEntity.ok(isDuplicate);
	}

	@GetMapping("/members/validate-password")
	public ResponseEntity<Boolean> validatePassword(@RequestParam String memberPwd) {
		Boolean isValid = memberService.isValidPassword(memberPwd);
		return ResponseEntity.ok(isValid);
	}

	@PostMapping("/members/search")
	public List<Member> searchMembersByField(@RequestParam(value = "memberId", required = false) String memberId,
											 @RequestParam(value = "memberName", required = false) String memberName,
											 @RequestParam(value = "memberPhone", required = false) String memberPhone,
											 @RequestParam(value = "startDate", required = false) String startDate,
											 @RequestParam(value = "endDate", required = false) String endDate) {

		MemberSearchDto memberSearchDto = new MemberSearchDto();
		memberSearchDto.setMemberId(memberId);
		memberSearchDto.setMemberName(memberName);
		memberSearchDto.setMemberPhone(memberPhone);
		memberSearchDto.setStartDate(startDate);
		memberSearchDto.setEndDate(endDate);

		List<Member> members = memberService.searchMembers(memberSearchDto);
		return members;
	}

	// download member search list
	@GetMapping("/members/download")
	public void exportExcelFile(HttpServletResponse response) throws IOException {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=members.xlsx";
		response.setHeader(headerKey, headerValue);
		List<MemberDto> memberListDto = memberService.getAllMembers();
		MemberExcelExporter generator = new MemberExcelExporter(memberListDto);
		generator.generateExcelFile(response);
	}


	// get member by id
	@GetMapping("/members/{memberNo}")
	public Optional<Member> getMemberNo(@PathVariable(value = "memberNo") Long memberNo){;
		Optional<Member> member = memberService.getMemberByNo(memberNo);
		return member;
	}

	// update member by id
	@PutMapping("/members/update/{memberNo}")
	public ResponseEntity<?> updateMemberByNo(@PathVariable(value="memberNo") Long memberNo,
											  @RequestBody MemberUpdateDto memberUpdateDto) throws NotFoundException {
		Member updatedMember = memberService.updateMember(memberUpdateDto);
		return ResponseEntity.ok(updatedMember);
	}

	// delete member by id
	@DeleteMapping("/members/delete/{memberNo}")
	public String deleteMember(@PathVariable(value="memberNo") long memberNo) {
		boolean memberDelete = memberService.deleteMember(memberNo);
		return "deleted";
	}
}
