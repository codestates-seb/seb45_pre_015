package com.preproject.seb_pre_15.question.repository;


import com.preproject.seb_pre_15.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  //맴버별 질문 조회 쿼리문입니다
  Page<Question> findByMemberMemberId(long memberId, Pageable pageable);
  
  //검색어로 제목, 본문 검색 쿼리문입니다
  @Query("SELECT q FROM Question q WHERE q.title LIKE %:searchWord% OR q.body LIKE %:searchWord%")
  Page<Question> findBySearchWordQuestion(@Param("searchWord") String searchWord, Pageable pageable);
}