package com.preproject.seb_pre_15.member.controller;

import com.preproject.seb_pre_15.member.dto.MemberPatchDto;
import com.preproject.seb_pre_15.member.dto.MemberResponseDto;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.mapper.MemberMapper;
import com.preproject.seb_pre_15.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @GetMapping("{memberId}")
    public ResponseEntity memberDetails(@PathVariable("memberId") @Positive Long memberId){
        Member member = memberService.findMember(memberId);
        MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{memberId}")
    public ResponseEntity memberUpdate(@PathVariable("memberId") @Positive Long memberId,
                                       @Valid @RequestBody MemberPatchDto memberPatchDto){

        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(memberPatchDto),memberId);

        MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{memberId}")
    public ResponseEntity memberDelete(@PathVariable("memberId") @Positive Long memberId){
        memberService.deleteMember(memberId);

        return new ResponseEntity<>("success delete member",HttpStatus.OK);
    }







}
