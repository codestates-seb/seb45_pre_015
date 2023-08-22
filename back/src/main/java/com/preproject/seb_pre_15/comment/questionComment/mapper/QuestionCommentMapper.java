package com.preproject.seb_pre_15.comment.questionComment.mapper;

import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.comment.questionComment.dto.QuestionCommentDto;
import com.preproject.seb_pre_15.comment.questionComment.entity.QuestionComment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionCommentMapper {
   QuestionComment postToQuestionComment(QuestionCommentDto.Post post);

   QuestionComment patchToQuestionComment(QuestionCommentDto.Patch patch);

   default QuestionCommentDto.Response questionCommentToResponse(QuestionComment questionComment){
      if ( questionComment == null ) {
         return null;
      }

      QuestionCommentDto.Response response = new QuestionCommentDto.Response();

      response.setQuestionCommentId(questionComment.getQuestionCommentId());
      response.setBody( questionComment.getBody() );
      response.setMemberEmail(questionComment.getMember().getEmail());

      return response;
   };

   List<QuestionCommentDto.Response> questionCommentsToResponses(List<QuestionComment> QuestionComments);

}
