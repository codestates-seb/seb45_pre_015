package com.preproject.seb_pre_15.question.service;

import com.preproject.seb_pre_15.exception.BusinessLogicException;
import com.preproject.seb_pre_15.exception.ExceptionCode;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
  private final QuestionRepository questionRepository;
  
  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }
  //질문글 등록
  public Question createQuestion(Question Question){
    
    return questionRepository.save(Question);
  }
  //질문글 수정
  public Question updateQuestion(Question Question){
    
    return questionRepository.save(Question);
  }
  //질문글 쿼리 조회(게시판 -> 본문)
  public Question findQuestion(long QuestionId){
    return findVerifiedQuestionByQuery(QuestionId);
  }
  //질문글 전체조회(게시판 조회)
  public Page<Question> findQuestions(int page, int size) {
    return questionRepository.findAll(PageRequest.of(page, size,
        Sort.by("QuestionId").descending()));
  }
  //멤버별 질문글 전체조회
//  public Page<Question> findMemberQuestions(int page, int size, long memberId) {
//    Pageable pageable = PageRequest.of(page, size);
//    return questionRepository.findAllByMemberId(memberId, pageable);
//  }
  
  //본문 조회 로직
  private Question findVerifiedQuestionByQuery(long questionId) {
    Optional<Question> optionalQuestion = questionRepository.findById(questionId);
    Question findQuestion =
        optionalQuestion.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    
    return findQuestion;
  }
  
  public void deleteQuestion(long coffeeId) {
    Question question = findVerifiedQuestionByQuery(coffeeId);
    questionRepository.delete(question);
  }
}
