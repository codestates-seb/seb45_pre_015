package com.preproject.seb_pre_15.question.mapper;

import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.comment.questionComment.dto.QuestionCommentDto;
import com.preproject.seb_pre_15.question.dto.*;
import com.preproject.seb_pre_15.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
  default QuestionResponseDto questionToQuestionResponseDto(Question question){
    if ( question == null ) {
      return null;
    }

    QuestionResponseDto questionResponseDto = new QuestionResponseDto();

    if ( question.getQuestionId() != null ) {
      questionResponseDto.setQuestionId( question.getQuestionId() );
    }
    questionResponseDto.setTitle( question.getTitle() );
    questionResponseDto.setBody( question.getBody() );
    questionResponseDto.setView( question.getView() );
    questionResponseDto.setVote( question.getVote() );

    List<AnswerResponseDto> answerResponseDtos = question.getAnswers().stream().map(
            answer -> {
              AnswerResponseDto answerResponseDto = new AnswerResponseDto();
              answerResponseDto.setAnswerId(answer.getAnswerId());
              answerResponseDto.setBody(answer.getBody());
              answerResponseDto.setVote(answer.getVote());
              return answerResponseDto;
            }
    ).collect(Collectors.toList());

    List<QuestionCommentDto.Response> commentsResponses = question.getQuestionComments().stream().map(
            questionComment -> {
              QuestionCommentDto.Response commentsResponse = new QuestionCommentDto.Response();
              commentsResponse.setQuestionCommentId(questionComment.getQuestionCommentId());
              commentsResponse.setBody(questionComment.getBody());
              commentsResponse.setMemberEmail(questionComment.getMember().getEmail());
              return commentsResponse;
            }
    ).collect(Collectors.toList());
    questionResponseDto.setAnswers(answerResponseDtos);
    questionResponseDto.setComments(commentsResponses);

    return questionResponseDto;
  };
  Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);
  Question questionVotePatchDtoToQuestion(QuestionVotePatchDto questionVotePatchDto);
  Question questionPostDtoToQuestion(QuestionPostDto questionPatchDto);
  List<QuestionResponseDto> questionToQuestionResponseDtos(List<Question> questions);

  default QuestionResponsesDto questionResponsesDtoWithQuantity(List<QuestionResponseDto> questions, long quantity){
      QuestionResponsesDto questionResponsesDto = new QuestionResponsesDto();
      questionResponsesDto.setAllQuestions(questions);
      questionResponsesDto.setQuantity(quantity);
      return questionResponsesDto;

  }
}
