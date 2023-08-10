package com.preproject.seb_pre_15.answer.repository;


import com.preproject.seb_pre_15.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
  Page<Answer> findByMemberId(Long memberId, Pageable pageable);
}
