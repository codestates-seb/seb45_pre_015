package com.preproject.seb_pre_15.answer.mapper;

import com.preproject.seb_pre_15.answer.dto.AnswerPatchDto;
import com.preproject.seb_pre_15.answer.dto.AnswerPostDto;
import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.answer.dto.AnswerVotePatchDto;
import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto){
        Answer answer = new Answer();
        answer.setBody(answerPostDto.getBody());
        Question question = new Question();
        question.setQuestionId(answerPostDto.getQuestionId());
        answer.setQuestion(question);
        return answer;
    };
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);
    AnswerResponseDto answerToAnswerResponseDto(Answer answer);
    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);
    Answer answerVotePatchDtoToAnswer(AnswerVotePatchDto answerVotePatchDto);
}
