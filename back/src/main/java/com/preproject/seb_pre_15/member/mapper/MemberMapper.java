package com.preproject.seb_pre_15.member.mapper;

import com.preproject.seb_pre_15.member.dto.MemberPatchDto;
import com.preproject.seb_pre_15.member.dto.MemberResponseDto;
import com.preproject.seb_pre_15.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberResponseDto memberToMemberResponseDto(Member member);
    Member memberPatchDtoToMemberEntity(MemberPatchDto memberPatchDto);
}
