package com.preproject.seb_pre_15.question.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class QuestionResponsesDto {

    private long quantity;

    private List<QuestionResponseDto> allQuestions = new ArrayList<>();
}
