package com.preproject.seb_pre_15.comment.questionComment.controller;

import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.comment.answerComment.mapper.AnswerCommentMapper;
import com.preproject.seb_pre_15.comment.answerComment.service.AnswerCommentService;
import com.preproject.seb_pre_15.comment.questionComment.dto.QuestionCommentDto;
import com.preproject.seb_pre_15.comment.questionComment.entity.QuestionComment;
import com.preproject.seb_pre_15.comment.questionComment.mapper.QuestionCommentMapper;
import com.preproject.seb_pre_15.comment.questionComment.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/{member-id}/{question-id}/question-comments")
@RequiredArgsConstructor
public class QuestionCommentController {

    private final QuestionCommentMapper mapper;
    private final QuestionCommentService service;



    @PostMapping
    public ResponseEntity postQuestionComment(@Valid @RequestBody QuestionCommentDto.Post post,
                                            @Positive @PathVariable("member-id") long memberId,
                                            @Positive @PathVariable("question-id") long questionId){

        QuestionComment questionComment = service.createQuestionComment(mapper.postToQuestionComment(post),memberId,questionId);
        QuestionCommentDto.Response response = mapper.questionCommentToResponse(questionComment);

        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchAnswerComment(@Valid @RequestBody QuestionCommentDto.Patch patch,
                                             @Positive @PathVariable("member-id") long memberId,
                                             @Positive @PathVariable("question-id") long answerId,
                                             @Positive @PathVariable("comment-id") long questionCommentId
                                             ){
        QuestionComment questionComment = service.updateQuestionComment(mapper.patchToQuestionComment(patch),questionCommentId,memberId);
        QuestionCommentDto.Response response= mapper.questionCommentToResponse(questionComment);

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/{comment-id}")
    public ResponseEntity getQuestionComment( @Positive @PathVariable("comment-id") long questionCommentId){
        QuestionComment questionComment = service.getQuestionComment(questionCommentId);
        return new ResponseEntity(mapper.questionCommentToResponse(questionComment),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestionComments( @Positive @PathVariable("member-id") long memberId,
                                             @Positive @RequestParam("page") int page,
                                             @Positive @RequestParam("size") int size){

        Page<QuestionComment> questionCommentPage = service.getQuestionComment(memberId,page,size);
        List<QuestionCommentDto.Response> responses = mapper.questionCommentsToResponses(questionCommentPage.toList());

        return new ResponseEntity(responses,HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteAnswerComment(@Positive @PathVariable("comment-id") long questionCommentId){

        service.deleteQuestionComment(questionCommentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
