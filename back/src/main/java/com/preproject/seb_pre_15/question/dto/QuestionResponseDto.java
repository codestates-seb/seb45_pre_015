package com.preproject.seb_pre_15.question.dto;

import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.comment.questionComment.dto.QuestionCommentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class QuestionResponseDto {
  private long questionId;
  
  private String title;
  
  private String body;
  
  private Long view;

  private Long vote;
  
  private List<byte[]> img;
  
  private List<AnswerResponseDto> answers;
  
  private List<QuestionCommentDto.Response> comments;
}
