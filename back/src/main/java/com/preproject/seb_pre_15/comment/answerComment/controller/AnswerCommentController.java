package com.preproject.seb_pre_15.comment.answerComment.controller;

import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.comment.answerComment.mapper.AnswerCommentMapper;
import com.preproject.seb_pre_15.comment.answerComment.service.AnswerCommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/{member-id}/{answer-id}/answer-comments")
public class AnswerCommentController {

    private final AnswerCommentMapper mapper;
    private final AnswerCommentService service;

    public AnswerCommentController(AnswerCommentMapper mapper, AnswerCommentService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity postAnswerComment(@Valid @RequestBody AnswerCommentDto.Post post,
                                            @Positive @PathVariable("member-id") long memberId,
                                            @Positive @PathVariable("answer-id") long answerId){

        AnswerComment answerComment = service.createAnswerComment(mapper.postToAnswerComment(post),memberId,answerId);
        AnswerCommentDto.Response response = mapper.answerCommentToResponse(answerComment);

        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @PatchMapping("/{answerComment-id}")
    public ResponseEntity patchAnswerComment(@Valid @RequestBody AnswerCommentDto.Patch patch,
                                             @Positive @PathVariable("member-id") long memberId,
                                             @Positive @PathVariable("answer-id") long answerId,
                                             @Positive @PathVariable("answerComment-id") long answerCommentId
                                             ){
        AnswerComment answerComment = service.updateAnswerComment(mapper.patchToAnswerComment(patch),answerCommentId);
        AnswerCommentDto.Response response= mapper.answerCommentToResponse(answerComment);

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/{answerComment-id}")
    public ResponseEntity getAnswerComment( @Positive @PathVariable("answerComment-id") long answerCommentId){
        AnswerComment answerComment = service.getAnswerComment(answerCommentId);
        return new ResponseEntity(mapper.answerCommentToResponse(answerComment),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswerComments( @Positive @PathVariable("member-id") long memberId,
                                             @Positive @RequestParam("page") int page,
                                             @Positive @RequestParam("size") int size){

        Page<AnswerComment> answerCommentPage = service.getAnswerComments(memberId,page,size);
        List<AnswerCommentDto.Response> responses = mapper.answerCommentsToResponses(answerCommentPage.toList());

        return new ResponseEntity(responses,HttpStatus.OK);
    }

    @DeleteMapping("/{answerComment-id}")
    public ResponseEntity deleteAnswerComment(@Positive @PathVariable("answerComment-id") long answerCommentId){

        service.deleteAnswerComment(answerCommentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
