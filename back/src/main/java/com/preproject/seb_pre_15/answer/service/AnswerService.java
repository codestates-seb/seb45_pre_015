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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
  private final AnswerRepository answerRepository;
  private final QuestionService questionService;
  private final MemberService memberService;

  //답변글 등록
  public Answer createAnswer(Answer answer){
    Question question = questionService.findQuestion(answer.getQuestion().getQuestionId());
    Member member = memberService.findMember(answer.getMember().getMemberId());
    answer.setMember(member);
    answer.setQuestion(question);
    return answerRepository.save(answer);
  }
  //답변글 수정
  public Answer updateAnswer(Answer answer, long answerId, long memberId){
//    memberService.verifySameUser(memberId);
//    Answer updateFindAnswer = findAnswer(answerId);
//    updateFindAnswer.setBody(answer.getBody());
    Answer findAnswer = findAnswer(answerId);
    Long findAnswerMemberId = findAnswer.getMember().getMemberId();
    if(findAnswerMemberId.equals(memberId)){
      findAnswer.setBody(answer.getBody());
      return answerRepository.save(findAnswer);
    }else{
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to update this answer");
    }
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

  //답변글 삭제
  public void deleteAnswer(Long answerId, Long memberId) {
    if(answerId.equals(memberId)){
      Answer answer = findAnswer(answerId);
      answerRepository.delete(answer);
    }else{
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to delete this answer");
    }

  }
  //멤버별 답변글 전체조회
  public Page<Answer> findMemberAnswers(Long memberId, int page) {
    Pageable pageable = PageRequest.of(page, 15, Sort.by("answerId").descending());
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
  
  // 투표수 증감 로직
  @Transactional
  public Answer updateAnswerVote(HttpServletRequest request, HttpServletResponse response, Answer answer, String voteType) {
    Answer findAnswer = findVerifiedAnswerByQuery(answer.getAnswerId());
    
    //쿠키가 없으면 생성하고 투표수 반영
    if (shouldUpdateAnswerVote(request, response, findAnswer, answer, voteType)) {
      //어뷰징 방지 로직
      if (Math.abs(findAnswer.getVote() - answer.getVote()) != 1) {
        throw new BusinessLogicException(ExceptionCode.INVALID_VOTE);
      }
      findAnswer.setVote(answer.getVote());
      
      Cookie votedCookie = new Cookie("voted_answer_" + findAnswer.getAnswerId(), voteType);
      votedCookie.setMaxAge(86400);
      response.addCookie(votedCookie);
    }
    
    return answerRepository.save(findAnswer);
  }
  
  private boolean shouldUpdateAnswerVote(HttpServletRequest request, HttpServletResponse response, Answer findAnswer, Answer answer, String voteType) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("voted_answer_" + findAnswer.getAnswerId())) {
          //어뷰징 방지 로직
          if (Math.abs(findAnswer.getVote() - answer.getVote()) != 1) {
            throw new BusinessLogicException(ExceptionCode.INVALID_VOTE);
          }
          //쿠키가 있지만 이전 쿠키 타입이 다르면 값을 변경하고 투표수 반영
          if (cookie.getValue().equals("down") && voteType.equals("up")) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            findAnswer.setVote(answer.getVote());
          } else if (cookie.getValue().equals("up") && voteType.equals("down")) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            findAnswer.setVote(answer.getVote());
          }
          return false;
        }
      }
    }
    return true;
  }
}
