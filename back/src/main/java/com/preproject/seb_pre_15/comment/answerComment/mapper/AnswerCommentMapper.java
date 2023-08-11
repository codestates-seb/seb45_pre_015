package com.preproject.seb_pre_15.comment.answerComment.mapper;

import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerCommentMapper {
   AnswerComment postToAnswerComment(AnswerCommentDto.Post post);

   AnswerComment patchToAnswerComment(AnswerCommentDto.Patch patch);

   default AnswerCommentDto.Response answerCommentToResponse(AnswerComment answerComment){
      if ( answerComment == null ) {
         return null;
      }

      AnswerCommentDto.Response response = new AnswerCommentDto.Response();

      response.setAnswerCommentId(answerComment.getAnswerCommentId());
      response.setBody( answerComment.getBody() );
      response.setMemberEmail(answerComment.getMember().getEmail());

      return response;
   };

   List<AnswerCommentDto.Response> answerCommentsToResponses(List<AnswerComment> answerComments);

}
