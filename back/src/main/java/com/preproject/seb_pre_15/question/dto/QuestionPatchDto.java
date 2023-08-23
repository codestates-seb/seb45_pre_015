package com.preproject.seb_pre_15.question.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionPatchDto {
  private long questionId;
  
  private String title;
  
  private String body;
  
  private Long vote;
  
//  private String images;
}
