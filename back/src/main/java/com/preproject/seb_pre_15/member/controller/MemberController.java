package com.preproject.seb_pre_15.member.controller;

import com.preproject.seb_pre_15.image.service.ProfileImageService;
import com.preproject.seb_pre_15.member.dto.MemberPatchDto;
import com.preproject.seb_pre_15.member.dto.MemberResponseDto;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.mapper.MemberMapper;
import com.preproject.seb_pre_15.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final ProfileImageService profileImageService;

    @GetMapping("{member-Id}")
    public ResponseEntity memberDetails(@PathVariable("member-Id") @Positive Long memberId){
        Member member = memberService.findMember(memberId);
        MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
        response.setImg(profileImageService.getImage(memberId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity getMember(){
        System.out.println("++++이부분에서 지금 접속한 사용자를 읽어옵니다.!!!+++++"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        MemberResponseDto response= memberMapper.memberToMemberResponseDto(memberService.findMemberByEmail(email));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("{member-id}")
    public ResponseEntity memberUpdate(@PathVariable("member-id") @Positive Long memberId,
                                       @Valid @RequestBody MemberPatchDto memberPatchDto){

        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(memberPatchDto),memberId);

        MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
//    //프로필 이미지 수정 기능 추가
//    @PatchMapping("{member-id}")
//    public ResponseEntity memberUpdate(@RequestPart("image") MultipartFile image,
//                                       @PathVariable("member-id") @Positive Long memberId,
//                                       @Valid @RequestPart("json") MemberPatchDto memberPatchDto) throws IOException {
//
//        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(memberPatchDto),memberId);
//
//        MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
//        response.setImg(profileImageService.updateOrCreateProfileImage(image, memberId));
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    
    
    @DeleteMapping("{member-id}")
    public ResponseEntity memberDelete(@PathVariable("member-id") @Positive Long memberId){
        memberService.deleteMember(memberId);

        return new ResponseEntity<>("success delete member", HttpStatus.NO_CONTENT);
    }







}
