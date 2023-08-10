package com.preproject.seb_pre_15.question.controller;

import com.preproject.seb_pre_15.question.dto.QuestionPatchDto;
import com.preproject.seb_pre_15.question.dto.QuestionPostDto;
import com.preproject.seb_pre_15.question.dto.QuestionResponseDto;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.mapper.QuestionMepper;
import com.preproject.seb_pre_15.question.service.QuestionService;
import com.preproject.seb_pre_15.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {
  private final QuestionService questionService;
  private final QuestionMepper questionMepper;
  public QuestionController(QuestionService questionService, QuestionMepper questionMepper) {
    this.questionService = questionService;
    this.questionMepper = questionMepper;
  }
  
  @PostMapping
  public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto questionPostDto) {
    Question question = questionService.createQuestion(questionMepper.questionPostDtoToQuestion(questionPostDto));
    URI location = UriCreator.createUri("questions", question.getQuestionId());
    
    return ResponseEntity.created(location).build();
  }
  
  @PatchMapping("/{question-id}")
  public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                    @Valid @RequestBody QuestionPatchDto questionPatchDto) {
    questionPatchDto.setQuestionId(questionId);
    Question question = questionService.updateQuestion(questionMepper.questionPatchDtoToQuestion(questionPatchDto));
    QuestionResponseDto response = questionMepper.questionToQuestionResponseDto(question);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  @GetMapping("/{question-id}")
  public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {
    Question question = questionService.findQuestion(questionId);
    QuestionResponseDto response = questionMepper.questionToQuestionResponseDto(question);
    
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  @GetMapping
  public ResponseEntity getOrders(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size) {
    Page<Question> pageOrders = questionService.findQuestions(page - 1, size);
    List<Question> questions = pageOrders.getContent();
    List<QuestionResponseDto> response = questionMepper.questionToQuestionResponseDtos(questions);
    
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  @DeleteMapping("/{question-id}")
  public ResponseEntity qustionDelete(@PathVariable("questionId") @Positive Long questionId){
    questionService.deleteQuestion(questionId);
    
    return new ResponseEntity<>("success delete member",HttpStatus.OK);
  }
  
}
