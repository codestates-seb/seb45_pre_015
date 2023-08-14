package com.preproject.seb_pre_15.answer.repository;


import com.preproject.seb_pre_15.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByMemberMemberId(Long memberId, Pageable pageable);
//  Page<Answer> findByMemberId(Long memberId, Pageable pageable);
}
