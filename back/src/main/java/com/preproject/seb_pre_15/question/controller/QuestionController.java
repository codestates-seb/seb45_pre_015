package com.preproject.seb_pre_15.question.controller;

import com.preproject.seb_pre_15.question.dto.QuestionPatchDto;
import com.preproject.seb_pre_15.question.dto.QuestionPostDto;
import com.preproject.seb_pre_15.question.dto.QuestionResponseDto;
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
  public QuestionController(QuestionService questionService, QuestionMapper questionMepper) {
    this.questionService = questionService;
    this.questionMapper = questionMepper;
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
  
  //선택 질문 글 조회
  @GetMapping("/questions/{question-id}")
  public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {
    Question question = questionService.findQuestion(questionId);
    QuestionResponseDto response = questionMapper.questionToQuestionResponseDto(question);
    
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  //맴버별 질문 글 조회, 5개씩 출력됩니다
  @GetMapping("/{member-id}/questions")
  public ResponseEntity getMemberQuestion(
      @PathVariable("member-id") long memberId) {
    Page<Question> pageOrders = questionService.findMemberQuestions(memberId);
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
  @GetMapping("/questions/search_word")
  public ResponseEntity getQuestionSearch(@RequestParam(value = "searchWord" ) String searchWord) {
    Page<Question> pageOrders = questionService.findSearchWordQuestions(searchWord);
    List<Question> questions = pageOrders.getContent();
    List<QuestionResponseDto> response = questionMapper.questionToQuestionResponseDtos(questions);
    
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
}
