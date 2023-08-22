package com.preproject.seb_pre_15.question.controller;

import com.preproject.seb_pre_15.argumentresolver.LoginMemberId;
import com.preproject.seb_pre_15.image.service.QuestionImageService;
import com.preproject.seb_pre_15.question.dto.*;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.mapper.QuestionMapper;
import com.preproject.seb_pre_15.question.service.QuestionService;
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
public class QuestionController {
  private final QuestionService questionService;
  private final QuestionMapper questionMapper;
  private final QuestionImageService questionImageService;
  
  
  //질문 글 등록
  @PostMapping("/questions")
  public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto questionPostDto,
                                     @LoginMemberId Long memberId) {
    Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(questionPostDto), memberId);
//    URI location = UriCreator.createUri("questions", question.getQuestionId());
    QuestionResponseDto response = questionMapper.questionToQuestionResponseDto(question);
    return new ResponseEntity<>(response,HttpStatus.CREATED);
  }
  
  //이미지를 포함한 질문 글 등록
  @PostMapping("/questionsWithImg")
  public ResponseEntity createPostWithImages(@Valid @RequestPart("json") QuestionPostDto questionPostDto,
                                             @RequestPart("images") List<MultipartFile> images,
                                             @LoginMemberId Long memberId) {
    Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(questionPostDto), memberId);
    QuestionResponseDto response = questionMapper.questionToQuestionResponseDto(question);
    response.setImg(questionImageService.saveImages(images, question));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  //질문 글 수정
  //권한 설정을 위해 API 주소 변경
  @PatchMapping("/questions/{question-id}")
  public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                      @LoginMemberId @Positive long memberId,
                                      @Valid @RequestBody QuestionPatchDto questionPatchDto) {
    questionPatchDto.setQuestionId(questionId);
    Question question = questionService.updateQuestion(questionMapper.questionPatchDtoToQuestion(questionPatchDto), memberId);
    QuestionResponseDto response = questionMapper.questionToQuestionResponseDto(question);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  //이미지를 포함한 질문 글 수정
  @PatchMapping("/questionsWithImg/{question-id}")
  public ResponseEntity patchQuestion(@Valid @RequestPart("json") QuestionPatchDto questionPatchDto,
                                      @RequestPart("images") List<MultipartFile> images,
                                      @PathVariable("question-id") @Positive long questionId,
                                      @LoginMemberId @Positive long memberId) throws IOException {

    questionPatchDto.setQuestionId(questionId);
    Question question = questionService.updateQuestion(questionMapper.questionPatchDtoToQuestion(questionPatchDto), memberId);
    QuestionResponseDto response = questionMapper.questionToQuestionResponseDto(question);
    response.setImg(questionImageService.updateImages(images, questionId));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  //전체 질문 글 조회
  @GetMapping("/questions")
  public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size) {
    Page<Question> pageOrders = questionService.findQuestions(page - 1, size);
    List<Question> questions = pageOrders.getContent();
    List<QuestionResponseDto> response = questionMapper.questionToQuestionResponseDtos(questions);
    
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  //선택 질문 글 조회 + 쿠키 조회 및 조회수 증가
  @GetMapping("/questions/{question-id}")
  public ResponseEntity getQuestion(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("question-id") @Positive long questionId ) {
    Question question = questionService.findQuestion(questionId, request, response);
    System.out.println(question.getView()+"333333333333");
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    System.out.println(responseDto.getView()+"444444444444");
    responseDto.setImg(questionImageService.getQuestionImage(questionId)
        .stream().map(m->m.getImg()).collect(Collectors.toList()));
    System.out.println(responseDto.getView()+"55555555555");
    return new ResponseEntity<>(responseDto,HttpStatus.OK);
  }
  
  //맴버별 질문 글 조회, 15개씩 출력됩니다
  @GetMapping("/{member-id}/questions")
  public ResponseEntity getMemberQuestion(@Positive @RequestParam int page,
      @PathVariable("member-id") long memberId) {
    Page<Question> pageOrders = questionService.findMemberQuestions(page, memberId);
    List<Question> questions = pageOrders.getContent();
    List<QuestionResponseDto> response = questionMapper.questionToQuestionResponseDtos(questions);

    return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  //선택 질문 글 삭제
  @DeleteMapping("/questions/{question-id}")
  public ResponseEntity questionDelete(@PathVariable("question-id") @Positive Long questionId,
                                       @LoginMemberId Long memberId){
    questionService.deleteQuestion(questionId, memberId);
    
    return new ResponseEntity<>("success delete question", HttpStatus.NO_CONTENT);
  }
  
  //질문글 검색 기능
  @GetMapping("/questions/search-word")
  public ResponseEntity getQuestionSearch(@RequestParam(value = "search-word" ) String searchWord,
                                          @Positive int page) {
    Page<Question> pageOrders = questionService.findSearchWordQuestions(searchWord, page);
    List<Question> questions = pageOrders.getContent();
    List<QuestionResponseDto> response = questionMapper.questionToQuestionResponseDtos(questions);
    
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  //질문글 Top10 조회(게시판 조회)
  @GetMapping("/questions/top10")
  public ResponseEntity getQuestions() {
    Page<Question> pageOrders = questionService.findTopQuestions();
    List<Question> questions = pageOrders.getContent();
    List<QuestionResponseDto> response = questionMapper.questionToQuestionResponseDtos(questions);
    
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  //추천수 증가 로직
  @PatchMapping("/questions/{question-id}/votes-up")
  public ResponseEntity patchQuestionVoteUp(HttpServletRequest request, HttpServletResponse response,
                                          @PathVariable("question-id") @Positive long questionId,
                                          @Valid @RequestBody QuestionVotePatchDto questionVotePatchDto) {
    questionVotePatchDto.setQuestionId(questionId);
    Question question = questionMapper.questionVotePatchDtoToQuestion(questionVotePatchDto);
    question = questionService.updateQuestionVote(request, response, question, "up");
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }
  // 추천수 감소 로직
  @PatchMapping("/questions/{question-id}/votes-down")
  public ResponseEntity patchQuestionVoteDown(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable("question-id") @Positive long questionId,
                                            @Valid @RequestBody QuestionVotePatchDto questionVotePatchDto) {
    questionVotePatchDto.setQuestionId(questionId);
    Question question = questionMapper.questionVotePatchDtoToQuestion(questionVotePatchDto);
    question = questionService.updateQuestionVote(request, response, question, "down");
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/questions/total")
  public ResponseEntity getTotalNumberOfQuestion(){

   QuestionQuantityResponseDto questionQuantityResponseDto = questionMapper.totalNumToQuestionQuantityResponseDto(questionService.getQuestionQuantity());
  return new ResponseEntity<>(questionQuantityResponseDto, HttpStatus.OK);
  }
}
