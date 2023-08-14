package com.preproject.seb_pre_15.question.mapper;

import com.preproject.seb_pre_15.question.dto.QuestionPatchDto;
import com.preproject.seb_pre_15.question.dto.QuestionPostDto;
import com.preproject.seb_pre_15.question.dto.QuestionResponseDto;
import com.preproject.seb_pre_15.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
  QuestionResponseDto questionToQuestionResponseDto(Question question);
  Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);
  Question questionPostDtoToQuestion(QuestionPostDto questionPatchDto);
  List<QuestionResponseDto> questionToQuestionResponseDtos(List<Question> questions);
}
