package com.preproject.seb_pre_15.answer.service;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.answer.repository.AnswerRepository;
import com.preproject.seb_pre_15.exception.BusinessLogicException;
import com.preproject.seb_pre_15.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
  private final AnswerRepository answerRepository;
  
  public AnswerService(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }
  //답변글 등록
  public Answer createAnswer(Answer answer){
    
    return answerRepository.save(answer);
  }
  //답변글 수정
  public Answer updateAnswer(Answer answer){
    
    return answerRepository.save(answer);
  }
  //답변글 쿼리 조회(게시판 -> 본문)
  public Answer findAnswer(long answerId){
    return findVerifiedAnswerByQuery(answerId);
  }
  //답변글 전체조회(게시판 조회)
  public Page<Answer> findAnswers(int page, int size) {
    return answerRepository.findAll(PageRequest.of(page, size,
        Sort.by("answerId").descending()));
  }
  //멤버별 답변글 전체조회
//  public Page<Answer> findMemberAnswers(int page, int size, long memberId) {
//    Pageable pageable = PageRequest.of(page, size);
//    return answerRepository.findB(memberId, pageable);
//  }
  
  //본문 조회 로직
  private Answer findVerifiedAnswerByQuery(long answerId) {
    Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
    Answer findAnswer =
        optionalAnswer.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    
    return findAnswer;
  }
}
