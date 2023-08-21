package com.preproject.seb_pre_15.image.repository;

import com.preproject.seb_pre_15.image.entity.AnswerImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerImageRepository extends JpaRepository<AnswerImage, Long> {
  List<AnswerImage> findAnswerImageByAnswer_AnswerId(long answerId);
}