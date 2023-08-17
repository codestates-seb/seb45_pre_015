package com.preproject.seb_pre_15.comment.questionComment.repository;

import com.preproject.seb_pre_15.comment.questionComment.entity.QuestionComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    @Query(value = "select q from QuestionComment q where q.member.memberId = :memberId")
    Page<QuestionComment> findAllByMemberId(long memberId, Pageable pageable);

}
