package com.preproject.seb_pre_15.question.service;

import com.preproject.seb_pre_15.exception.BusinessLogicException;
import com.preproject.seb_pre_15.exception.ExceptionCode;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.repository.QuestionRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
  public Page<Question> findMemberQuestions(long memberId) {
    Pageable pageable = PageRequest.of(0, 5, Sort.by("QuestionId").descending());
    Page<Question> optionalPage = questionRepository.findByMemberMemberId(memberId, pageable);
    
    return optionalPage;
  }
  
    //본문 조회 로직
  private Question findVerifiedQuestionByQuery(long questionId) {
    Optional<Question> optionalQuestion = questionRepository.findById(questionId);
    Question findQuestion =
        optionalQuestion.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    //마이페이지 게시글 검색용 에러 로그 분리필요
    return findQuestion;
  }
  
  public void deleteQuestion(long coffeeId) {
    Question question = findVerifiedQuestionByQuery(coffeeId);
    questionRepository.delete(question);
  }
}
