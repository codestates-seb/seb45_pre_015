package com.preproject.seb_pre_15.question.dto;

import com.preproject.seb_pre_15.answer.entity.Answer;
import java.util.List;

public class QuestionResponseDto {
  private long questionId;
  
  private String title;
  
  private String body;
  
  private Long memberId;
  
  private List<Answer> answers;
  
//private List<questionComment> questionComments;
}
