package com.preproject.seb_pre_15.comment.answerComment.repository;

import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
    @Query(value = "select a from AnswerComment a where a.member.memberId = :memberId")
    Page<AnswerComment> findAllByMemberId(long memberId, Pageable pageable);

}
