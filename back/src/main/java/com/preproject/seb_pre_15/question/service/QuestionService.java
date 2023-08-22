package com.preproject.seb_pre_15.question.service;

import com.preproject.seb_pre_15.exception.BusinessLogicException;
import com.preproject.seb_pre_15.exception.ExceptionCode;
import com.preproject.seb_pre_15.member.service.MemberService;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  private final MemberService memberService;
  //질문글 등록
  public Question createQuestion(Question question, Long memberId) {
    question.setMember(memberService.findMember(memberId));
    return questionRepository.save(question);
  }
  
  //질문글 수정
  public Question updateQuestion(Question question,long memberId) {
    Question findQuestion = findQuestion(question.getQuestionId());
    Long findQuestionMemberId = findQuestion.getMember().getMemberId();
    if(findQuestionMemberId.equals(memberId)){
      findQuestion.setBody(question.getBody());
      return questionRepository.save(findQuestion);
    }else{
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to update this question");
    }
//    memberService.verifySameUser(memberId);
    
//    return questionRepository.save(question);
  }
  
  //Answer 조회용 Question 조회
  public Question findQuestion(long questionId) {
    return findVerifiedQuestionByQuery(questionId);
  }
  
  //질문글 조회(게시판 -> 본문), 해당 질문글 조회수 증가
  public Question findQuestion(long questionId, HttpServletRequest request, HttpServletResponse response) {
    Question findQuestion = findVerifiedQuestionByQuery(questionId);
    
    return questionRepository.save(addQuestionView(request, response, findQuestion));
  }
  
  //조회수 증가, 조회글 쿠키 생성 로직
  private Question addQuestionView(HttpServletRequest request, HttpServletResponse response, Question findQuestion) {
    if (shouldUpdateQuestionView(request, findQuestion)) {// 쿠키 조회, 없으면 조회수 증가 + 쿠키 생성
      findQuestion.setView(findQuestion.getView() + 1);
      
      Cookie viewedCookie = new Cookie("viewed_question_" + findQuestion.getQuestionId(), "true");
      viewedCookie.setMaxAge(86400); // 쿠키 만료시간 하루로 설정
      response.addCookie(viewedCookie);
    }
    return findQuestion;
  }
  
  //쿠키 조회 로직
  private boolean shouldUpdateQuestionView(HttpServletRequest request, Question question) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("viewed_question_" + question.getQuestionId())) {
          return false; //배열 값 중에 질문글 쿠키가 있다면 조회수 로직을 올리지 않습니다
        }
      }
    }
    return true; //배열에서 없으면 true 리턴 -> 조회수 증가
  }
  
  //질문글 전체조회(게시판 조회)
  public Page<Question> findQuestions(int page, int size) {
    questionRepository.count();
    return questionRepository.findAll(PageRequest.of(page, size,
        Sort.by(Sort.Direction.DESC, "questionId").descending()));
  }
  
  //질문글 Top10 조회(게시판 조회)
  public Page<Question> findTopQuestions() {
    return questionRepository.findAll(PageRequest.of(0, 10,
        Sort.by("vote").descending()));
  }
  
  //멤버별 질문글 전체조회
  //조회되는 엔티티 값에 따라 page값을 설정해야 합니다, 예로 게시글이 총 30개가 있으면 page의 범위는 0~5까지가 됩니다)
  public Page<Question> findMemberQuestions(int page,long memberId) {
    Pageable pageable = PageRequest.of(page, 15, Sort.by("questionId").descending());
    Page<Question> findQuestions = questionRepository.findByMemberMemberId(memberId, pageable);
    
    return findQuestions;
  }
  
  //본문 조회 로직
  private Question findVerifiedQuestionByQuery(long questionId) {
    Optional<Question> optionalQuestion = questionRepository.findById(questionId);
    //마이페이지 게시글 검색용 에러 로그 분리필요
    return optionalQuestion.orElseThrow(() ->
        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
  }
  
  public void deleteQuestion(long questionId, Long memberId) {
    Question findQuestion = findQuestion(questionId);
    Long findQuestionMemberId = findQuestion.getMember().getMemberId();
    if(findQuestionMemberId.equals(memberId)){
      questionRepository.delete(findQuestion);
    }else{
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to delete this question");
    }
//    Question question = findVerifiedQuestionByQuery(questionId);
//    questionRepository.delete(question);
  }
  
  //질문 글 검색 기능, 15개씩 출력됩니다
  public Page<Question> findSearchWordQuestions(String searchWord, int page) {
    Pageable pageable = PageRequest.of(page, 15, Sort.by("questionId").descending());
    
    return questionRepository.findBySearchWordQuestion(searchWord, pageable);
  }
  
  //투표수 증감 및 쿠키 생성 로직
  @Transactional
  public Question updateQuestionVote(HttpServletRequest request, HttpServletResponse response, Question question, String voteType) {
    Question findQuestion = findVerifiedQuestionByQuery(question.getQuestionId());
    
    //쿠키가 없으면 생성하고 투표수 반영
    if (shouldUpdateQuestionVote(request, response, findQuestion, question, voteType)) {
      //어뷰징 방지 로직
      if (Math.abs(findQuestion.getVote() - question.getVote()) != 1) {
        throw new BusinessLogicException(ExceptionCode.INVALID_VOTE);
      }
      findQuestion.setVote(question.getVote());
      
      Cookie votedCookie = new Cookie("voted_question_" + findQuestion.getQuestionId(), voteType);
      votedCookie.setMaxAge(86400);
      response.addCookie(votedCookie);
    }
    return questionRepository.save(findQuestion);
  }
  //투표 쿠키 조회 및 기존 투표 수정 로직
  private boolean shouldUpdateQuestionVote(HttpServletRequest request, HttpServletResponse response, Question findQuestion, Question question, String voteType) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("voted_question_" + findQuestion.getQuestionId())) {
          //어뷰징 방지 로직
          if (Math.abs(findQuestion.getVote() - question.getVote()) != 1) {
            throw new BusinessLogicException(ExceptionCode.INVALID_VOTE);
          }
          //쿠키가 있지만 이전 쿠키 타입이 다르면 쿠키 삭제하고 값을 반영
            if (cookie.getValue().equals("down") && voteType.equals("up")) {
              cookie.setMaxAge(0);
              response.addCookie(cookie);
              findQuestion.setVote(question.getVote());
            } else if (cookie.getValue().equals("up") && voteType.equals("down")) {
              cookie.setMaxAge(0);
              response.addCookie(cookie);
              findQuestion.setVote(question.getVote());
            }
          return false;
        }
      }
    }
    return true;
  }
  
}

