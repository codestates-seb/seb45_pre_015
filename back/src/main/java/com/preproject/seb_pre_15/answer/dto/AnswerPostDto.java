package com.preproject.seb_pre_15.answer.dto;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.entity.Question;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerPostDto {
    @NotNull
    private String body;
    private Long memberId;
    private Long questionId;

}
