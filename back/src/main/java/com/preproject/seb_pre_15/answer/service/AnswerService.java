package com.preproject.seb_pre_15.answer.service;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.answer.repository.AnswerRepository;
import com.preproject.seb_pre_15.exception.BusinessLogicException;
import com.preproject.seb_pre_15.exception.ExceptionCode;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.service.MemberService;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
  private final AnswerRepository answerRepository;
  private final QuestionService questionService;
  private final MemberService memberService;
  
//  public AnswerService(AnswerRepository answerRepository) {
//    this.answerRepository = answerRepository;
//  }
  //답변글 등록
  public Answer createAnswer(Answer answer,  Long QuestionId, Long memberId){
    Question question = questionService.findQuestion(QuestionId);
    Member member = memberService.findMember(memberId);
    answer.setMember(member);
    answer.setQuestion(question);
    return answerRepository.save(answer);
  }
  //답변글 수정
  public Answer updateAnswer(Answer answer, Long answerId){
    Answer updateFindAnswer = findAnswer(answerId);
    updateFindAnswer.setBody(answer.getBody());
    return answerRepository.save(updateFindAnswer);
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
  public void deleteAnswer(Long answerId) {
    Answer answer = findAnswer(answerId);
    answerRepository.delete(answer);
  }
  //멤버별 답변글 전체조회
  public Page<Answer> findMemberAnswers(Long memberId) {
    Pageable pageable = PageRequest.of(0, 5, Sort.by("answerId").descending());
    Page<Answer> optionalPage = answerRepository.findByMemberMemberId(memberId, pageable);

    return optionalPage;

  }
  //본문 조회 로직

  private Answer findVerifiedAnswerByQuery(long answerId) {
    Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
    Answer findAnswer =
        optionalAnswer.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

    return findAnswer;
  }
}
