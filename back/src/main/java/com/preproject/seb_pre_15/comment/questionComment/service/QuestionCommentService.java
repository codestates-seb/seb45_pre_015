package com.preproject.seb_pre_15.comment.questionComment.service;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.answer.repository.AnswerRepository;
import com.preproject.seb_pre_15.answer.service.AnswerService;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.comment.answerComment.repository.AnswerCommentRepository;
import com.preproject.seb_pre_15.comment.questionComment.entity.QuestionComment;
import com.preproject.seb_pre_15.comment.questionComment.repository.QuestionCommentRepository;
import com.preproject.seb_pre_15.exception.BusinessLogicException;
import com.preproject.seb_pre_15.exception.ExceptionCode;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.repository.MemberRepository;
import com.preproject.seb_pre_15.member.service.MemberService;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.repository.QuestionRepository;
import com.preproject.seb_pre_15.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionCommentService {

    private final QuestionCommentRepository repository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    public QuestionCommentService(QuestionCommentRepository repository,
                                  MemberService memberService,
                                  MemberRepository memberRepository,
                                  QuestionService questionService,
                                  QuestionRepository questionRepository) {
        this.repository = repository;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    public QuestionComment createQuestionComment(QuestionComment questionComment, long memberId, long answerId){

        Member findMember = memberService.findVerifiedMember(memberId);
        findMember.addQuestionComment(questionComment);

        Question findQuestion = questionService.findQuestion(answerId);
        findQuestion.addQuestionComment(questionComment);

        questionComment.setMember(findMember);
        questionComment.setQuestion(findQuestion);
        QuestionComment questionCommentToSave = repository.save(questionComment);
        memberRepository.save(findMember);
        questionRepository.save(findQuestion);
        return questionCommentToSave;
    }

    public QuestionComment updateQuestionComment(QuestionComment questionComment, long questionCommentId, long memberId){

        memberService.verifySameUser(memberId);

        QuestionComment findComment = verifyExistComment(questionCommentId);
        Optional.ofNullable(questionComment.getBody())
                .ifPresent(body->findComment.setBody(body));
        QuestionComment questionCommentToSave = repository.save(findComment);
        return questionCommentToSave;
    }

    public QuestionComment getQuestionComment(long questionCommentId){
       return verifyExistComment(questionCommentId);
    }

    public Page<QuestionComment> getQuestionComment(long memberId, int page, int size){
         Page<QuestionComment> questionCommentPage = repository.findAllByMemberId(
                 memberId, PageRequest.of(page-1,size, Sort.by("questionCommentId")));
         return questionCommentPage;
    }

    public void deleteQuestionComment(long questionCommentId){
        QuestionComment findQuestionComment = verifyExistComment(questionCommentId);
        repository.delete(findQuestionComment);
    }

        private QuestionComment verifyExistComment(long questionCommentId) {
       Optional<QuestionComment> findQuestionComment = repository.findById(questionCommentId);
            QuestionComment questionComment = findQuestionComment.orElseThrow(()->
               new BusinessLogicException(ExceptionCode.COMMENT_NOT_EXIST));
       return questionComment;
    }
}
