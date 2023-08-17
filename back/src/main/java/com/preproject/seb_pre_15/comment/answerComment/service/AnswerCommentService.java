package com.preproject.seb_pre_15.comment.answerComment.service;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.answer.repository.AnswerRepository;
import com.preproject.seb_pre_15.answer.service.AnswerService;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.comment.answerComment.repository.AnswerCommentRepository;
import com.preproject.seb_pre_15.exception.BusinessLogicException;
import com.preproject.seb_pre_15.exception.ExceptionCode;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.repository.MemberRepository;
import com.preproject.seb_pre_15.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerCommentService {

    private final AnswerCommentRepository repository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;

    public AnswerCommentService(AnswerCommentRepository repository,
                                MemberService memberService,
                                MemberRepository memberRepository,
                                AnswerService answerService,
                                AnswerRepository answerRepository) {
        this.repository = repository;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.answerService = answerService;
        this.answerRepository = answerRepository;
    }

    public AnswerComment createAnswerComment(AnswerComment answerComment, long memberId, long answerId){

        Member findMember = memberService.findVerifiedMember(memberId);
        findMember.addAnswerComment(answerComment);

        Answer findAnswer = answerService.findAnswer(answerId);
        findAnswer.addAnswerComment(answerComment);

        answerComment.setMember(findMember);
        answerComment.setAnswer(findAnswer);
        AnswerComment answerCommentToSave = repository.save(answerComment);
        memberRepository.save(findMember);
        answerRepository.save(findAnswer);
        return answerCommentToSave;
    }

    public AnswerComment updateAnswerComment(AnswerComment answerComment, long answerCommentId, long memberId){

        memberService.verifySameUser(memberId);

        AnswerComment findComment = verifyExistComment(answerCommentId);
        Optional.ofNullable(answerComment.getBody())
                .ifPresent(body->findComment.setBody(body));
        AnswerComment answerCommentToSave = repository.save(findComment);
        return answerCommentToSave;
    }

    public AnswerComment getAnswerComment(long answerCommentId){
       return verifyExistComment(answerCommentId);
    }

    public Page<AnswerComment> getAnswerComments(long memberId, int page, int size){
         Page<AnswerComment> answerCommentPage = repository.findAllByMemberId(
                 memberId, PageRequest.of(page-1,size, Sort.by("answerCommentId")));
         return answerCommentPage;
    }

    public void deleteAnswerComment(long answerCommentId){
        AnswerComment findAnswerComment = verifyExistComment(answerCommentId);
        repository.delete(findAnswerComment);
    }

    private AnswerComment verifyExistComment(long answerCommentId) {
       Optional<AnswerComment> findAnswerComment = repository.findById(answerCommentId);
       AnswerComment answerComment = findAnswerComment.orElseThrow(()->
               new BusinessLogicException(ExceptionCode.COMMENT_NOT_EXIST));
       return answerComment;
    }
}
