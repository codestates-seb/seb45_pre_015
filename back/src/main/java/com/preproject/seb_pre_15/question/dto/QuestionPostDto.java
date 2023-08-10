package com.preproject.seb_pre_15.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public class QuestionPostDto {
  
  private String title;
  
  private String body;


//  private String images;
//
//  private Long vote;
}
