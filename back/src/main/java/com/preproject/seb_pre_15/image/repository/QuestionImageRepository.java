package com.preproject.seb_pre_15.image.repository;

import com.preproject.seb_pre_15.image.entity.QuestionImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionImageRepository extends JpaRepository<QuestionImage, Long> {
    List<QuestionImage> findQuestionImagesByQuestion_QuestionId(long questionId);
}