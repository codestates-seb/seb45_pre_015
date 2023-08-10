package com.preproject.seb_pre_15.question.repository;


import com.preproject.seb_pre_15.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {


//    @Query(value = "SELECT q fROM Question q where q.")
//    Page<Question> findAllByMemberId(Long memberId, Pageable pageable);
}
