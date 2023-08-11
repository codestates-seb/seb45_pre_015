package com.preproject.seb_pre_15.answer.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerPatchDto {
    private Long answerId;
    @NotNull
    private String body;

}
