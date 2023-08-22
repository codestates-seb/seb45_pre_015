package com.preproject.seb_pre_15.answer.controller;

import com.preproject.seb_pre_15.answer.dto.AnswerPatchDto;
import com.preproject.seb_pre_15.answer.dto.AnswerPostDto;
import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.answer.dto.AnswerVotePatchDto;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
@Validated
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    // 답변 생성
    @PostMapping("/answers")
    public ResponseEntity createAnswer(@RequestPart("json") AnswerPostDto answerPostDto,
                                       @RequestPart("image") MultipartFile imageFile,
                                       @LoginMemberId Long memberId) throws IOException {
        Answer answer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(answerPostDto, memberId));
        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//    @PostMapping("/answers")
//    public ResponseEntity createAnswer(@RequestPart("json") AnswerPostDto answerPostDto,
//                                       @RequestPart("image") MultipartFile imageFile,
//                                       @LoginMemberId Long memberId) throws IOException {
//        Answer answer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(answerPostDto), memberId);
//        if (!imageFile.isEmpty()) {imageService.saveImage(imageFile, memberId, answer);}
//
//        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
//        return new ResponseEntity<>(response,HttpStatus.CREATED);
//    }

    // 해당 답변 수정
    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity updateAnswer(@RequestBody AnswerPatchDto answerPatchDto,
                                       @PathVariable("answer-id") @Positive Long answerId,
                                       @Positive @LoginMemberId Long memberId){
        Answer answer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(answerPatchDto), answerId, memberId);
        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 해당 답변 조회
    @GetMapping("/answers/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive Long answerId){
        Answer answer = answerService.findAnswer(answerId);
        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 모든 답변 조회
    @GetMapping("/answers")
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Answer> pageAnswers = answerService.findAnswers(page-1, size);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerResponseDto> response = answerMapper.answersToAnswerResponseDtos(answers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 멤버 별 답변 조회
    @GetMapping("/{member-id}/answers")
    public ResponseEntity getMemberAnswers(@Positive @RequestParam int page,
            @PathVariable("member-id") @Positive Long memberId){
        Page<Answer> pageAnswers = answerService.findMemberAnswers(memberId, page);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerResponseDto> response = answerMapper.answersToAnswerResponseDtos(answers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 해당 답변 삭제
    @DeleteMapping("/answers/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive Long answerId,
                                       @LoginMemberId Long memberId) {
        answerService.deleteAnswer(answerId, memberId);
        return new ResponseEntity<>("success delete answer",HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/answers/{answer-id}/votes-up")
    public ResponseEntity patchAnswerVoteUp(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable("answer-id") @Positive long answerId,
                                            @Valid @RequestBody AnswerVotePatchDto answerVotePatchDto) {
        answerVotePatchDto.setAnswerId(answerId);
        Answer answer = answerMapper.answerVotePatchDtoToAnswer(answerVotePatchDto);
        answer = answerService.updateAnswerVote(request, response, answer, "up");
        AnswerResponseDto responseDto = answerMapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    // 추천수 감소 로직
    @PatchMapping("/answers/{answer-id}/votes-down")
    public ResponseEntity patchAnswerVoteDown(HttpServletRequest request, HttpServletResponse response,
                                              @PathVariable("answer-id") @Positive long answerId,
                                              @Valid @RequestBody AnswerVotePatchDto answerVotePatchDto) {
        answerVotePatchDto.setAnswerId(answerId);
        Answer answer = answerMapper.answerVotePatchDtoToAnswer(answerVotePatchDto);
        answer = answerService.updateAnswerVote(request, response, answer, "down");
        AnswerResponseDto responseDto = answerMapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
