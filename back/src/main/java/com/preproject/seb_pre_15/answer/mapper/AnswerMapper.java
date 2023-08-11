package com.preproject.seb_pre_15.answer.mapper;

import com.preproject.seb_pre_15.answer.dto.AnswerPatchDto;
import com.preproject.seb_pre_15.answer.dto.AnswerPostDto;
import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);
    AnswerResponseDto answerToAnswerResponseDto(Answer answer);
    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);
}
