package com.preproject.seb_pre_15.answer.dto;

import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerResponseDto {
    
    private Long answerId;

    private String memberEmail;
    
    private String body;
    
    private Long vote;  
    
    private List<byte[]> img;

    private List<AnswerCommentDto.Response> comments;
}
