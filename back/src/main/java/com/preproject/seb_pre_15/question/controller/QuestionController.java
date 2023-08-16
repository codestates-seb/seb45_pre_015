package com.preproject.seb_pre_15.question.controller;

import com.preproject.seb_pre_15.question.dto.QuestionPatchDto;
import com.preproject.seb_pre_15.question.dto.QuestionPostDto;
import com.preproject.seb_pre_15.question.dto.QuestionResponseDto;
import com.preproject.seb_pre_15.question.dto.QuestionVotePatchDto;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.mapper.QuestionMapper;

import com.preproject.seb_pre_15.question.service.QuestionService;
import com.preproject.seb_pre_15.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
@Validated
public class QuestionController {
  private final QuestionService questionService;
  private final QuestionMapper questionMapper;
  public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
    this.questionService = questionService;
    this.questionMapper = questionMapper;
  }
  
  //질문 글 등록
  @PostMapping("/questions")
  public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto questionPostDto) {
    Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(questionPostDto));
//    URI location = UriCreator.createUri("questions", question.getQuestionId());
    QuestionResponseDto response = questionMapper.questionToQuestionResponseDto(question);
    return new ResponseEntity<>(response,HttpStatus.CREATED);
  }
  
  //질문 글 수정
  @PatchMapping("/questions/{question-id}")
  public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                    @Valid @RequestBody QuestionPatchDto questionPatchDto) {
    questionPatchDto.setQuestionId(questionId);
    Question question = questionService.updateQuestion(questionMapper.questionPatchDtoToQuestion(questionPatchDto));
    QuestionResponseDto response = questionMapper.questionToQuestionResponseDto(question);
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
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    
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
  public ResponseEntity questionDelete(@PathVariable("question-id") @Positive Long questionId){
    questionService.deleteQuestion(questionId);
    
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
}
