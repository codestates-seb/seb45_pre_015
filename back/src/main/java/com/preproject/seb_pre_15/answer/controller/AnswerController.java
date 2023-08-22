package com.preproject.seb_pre_15.answer.controller;

import com.preproject.seb_pre_15.answer.dto.AnswerPatchDto;
import com.preproject.seb_pre_15.answer.dto.AnswerPostDto;
import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.answer.dto.AnswerVotePatchDto;
import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.answer.mapper.AnswerMapper;
import com.preproject.seb_pre_15.answer.service.AnswerService;
import com.preproject.seb_pre_15.argumentresolver.LoginMemberId;
import com.preproject.seb_pre_15.image.service.AnswerImageService;
import lombok.RequiredArgsConstructor;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping
@Validated
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final AnswerImageService answerImageService;

     //답변 생성
    @PostMapping("/answers")
    public ResponseEntity createAnswer(@RequestBody  AnswerPostDto answerPostDto,
                                       @LoginMemberId Long memberId) {
        Answer answer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(answerPostDto, memberId));
        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
//    //이미지를 포함한 질문 글 등록
//    @PostMapping("/answers")
//    public ResponseEntity createPostWithImages(@Valid @RequestPart("json") AnswerPostDto answerPostDto,
//                                               @RequestPart("images") List<MultipartFile> images,
//                                               @LoginMemberId Long memberId) {
//        Answer answer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(answerPostDto, memberId));
//        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
//        response.setImg(answerImageService.saveImages(images, answer));
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

     //해당 답변 수정
    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity updateAnswer(@RequestBody AnswerPatchDto answerPatchDto,
                                       @PathVariable("answer-id") @Positive Long answerId,
                                       @Positive @LoginMemberId Long memberId){
        Answer answer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(answerPatchDto), answerId, memberId);
        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    //이미지를 포함한 질문 글 수정
//    @PatchMapping("/answers/{answer-id}")
//    public ResponseEntity updateAnswer(@Valid @RequestPart("json") AnswerPatchDto answerPatchDto,
//                                      @RequestPart("images") List<MultipartFile> images,
//                                      @PathVariable("answer-id") @Positive long answerId,
//                                      @LoginMemberId @Positive long memberId) throws IOException {
//
//        answerPatchDto.setAnswerId(answerId);
//        Answer answer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(answerPatchDto), answerId, memberId);
//        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
//        response.setImg(answerImageService.updateImages(images, answerId));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    
    // 해당 답변 조회
    @GetMapping("/answers/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive Long answerId){
        Answer answer = answerService.findAnswer(answerId);
        AnswerResponseDto response = answerMapper.answerToAnswerResponseDto(answer);
        response.setImg(answerImageService.getAnswerImage(answerId)
            .stream().map(m->m.getImg()).collect(Collectors.toList()));
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 해당 질문 모든 답변
//    @GetMapping("/answers")
//    public ResponseEntity getAnswers(@Re)

//     모든 답변 조회
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
