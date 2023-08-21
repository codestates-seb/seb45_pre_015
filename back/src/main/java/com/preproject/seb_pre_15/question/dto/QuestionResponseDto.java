package com.preproject.seb_pre_15.question.dto;

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
  
// private List<Answer> answers;
  
// private List<questionComment> questionComments;
}
