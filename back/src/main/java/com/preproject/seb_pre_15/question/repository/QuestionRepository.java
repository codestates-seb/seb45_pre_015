package com.preproject.seb_pre_15.question.repository;


import com.preproject.seb_pre_15.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  Page<Question> findByMemberMemberId(long memberId, Pageable pageable);
//
//    @Query("SELECT q FROM Question q WHERE q.member.memberId = :memberId")
//    Page<Question> findByMemberIdQuestions(long memberId, Pageable pageable);
}