package com.preproject.seb_pre_15.answer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerResponseDto {
    
    private Long answerId;
    
    private String body;
    
    private Long vote;
  
    private List<byte[]> img;
}
