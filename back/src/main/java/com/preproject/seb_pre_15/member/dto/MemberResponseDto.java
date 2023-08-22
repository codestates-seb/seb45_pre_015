package com.preproject.seb_pre_15.member.dto;

import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import com.preproject.seb_pre_15.question.dto.QuestionResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MemberResponseDto {
    private Long memberId;
    private String name;
    private String email;
    private List<String> roles;
    private List<QuestionResponseDto> questions;
    private List<AnswerResponseDto> answers;
    private List<AnswerCommentDto.Response> answerComments;
    private byte[] img;
    private String profilePic;
}
