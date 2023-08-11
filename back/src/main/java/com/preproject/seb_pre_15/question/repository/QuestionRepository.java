package com.preproject.seb_pre_15.question.repository;


import com.preproject.seb_pre_15.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  Optional<Question> findByAllMemberId(long memberId, Pageable pageable);

//    @Query(value = "SELECT q fROM Question q where q.")
//    Page<Question> findAllByMemberId(Long memberId, Pageable pageable);
}
