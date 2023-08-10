package com.preproject.seb_pre_15.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MemberResponseDto {
    private Long member_id;
    private String name;
    private String email;
    private List<String> roles;
//    private List<QuestionResponseDto> questions;
//    private List<AnswerResponseDto> answers;
//    private String img;
}
