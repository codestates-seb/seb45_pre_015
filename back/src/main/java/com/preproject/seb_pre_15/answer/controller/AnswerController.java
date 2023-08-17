package com.preproject.seb_pre_15.answer.controller;

import com.preproject.seb_pre_15.answer.dto.AnswerPatchDto;
import com.preproject.seb_pre_15.answer.dto.AnswerPostDto;
import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.answer.mapper.AnswerMapper;
import com.preproject.seb_pre_15.answer.service.AnswerService;
import com.preproject.seb_pre_15.argumentresolver.LoginMemberId;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping
@Validated
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;


    // 답변 생성
    @PostMapping("/answers")
    public ResponseEntity createAnswer(@RequestBody AnswerPostDto answerPostDto,
                                       @LoginMemberId Long memberId){
        System.out.println(memberId+"1111111111111111");
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));
        AnswerResponseDto response = mapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 해당 답변 수정
    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity updateAnswer(@RequestBody AnswerPatchDto answerPatchDto,
                                       @PathVariable("answer-id") @Positive Long answerId){
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto), answerId);
        AnswerResponseDto response = mapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 해당 답변 조회
    @GetMapping("/answers/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive Long answerId){
        Answer answer = answerService.findAnswer(answerId);
        AnswerResponseDto response = mapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 모든 답변 조회
    @GetMapping("/answers")
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Answer> pageAnswers = answerService.findAnswers(page-1, size);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerResponseDto> response = mapper.answersToAnswerResponseDtos(answers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 멤버 별 답변 조회
    @GetMapping("/{member-id}/answers")
    public ResponseEntity getMemberAnswers(@PathVariable("member-id") @Positive Long memberId){
        Page<Answer> pageAnswers = answerService.findMemberAnswers(memberId);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerResponseDto> response = mapper.answersToAnswerResponseDtos(answers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 해당 답변 삭제
    @DeleteMapping("/answers/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive Long answerId){
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>("success delete answer",HttpStatus.NO_CONTENT);
    }
}
