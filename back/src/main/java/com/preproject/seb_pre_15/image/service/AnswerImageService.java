package com.preproject.seb_pre_15.image.service;

import com.preproject.seb_pre_15.image.entity.QuestionImage;
import com.preproject.seb_pre_15.image.repository.ProfileImageRepository;
import com.preproject.seb_pre_15.image.repository.QuestionImageRepository;
import com.preproject.seb_pre_15.member.service.MemberService;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerImageService {
  
  private final QuestionImageRepository questionImageRepository;
  private final QuestionService questionService;
  
  //질문글 이미지 저장
  public List<byte[]> saveImages(List<MultipartFile> images, Question question){
    List<QuestionImage> saveImages = images.stream()
        .filter(multipartFile -> !multipartFile.isEmpty())
        .map(multipartFile -> {
          QuestionImage image = new QuestionImage();
          try {
            image.setQuestion(question);
            image.setImg(multipartFile.getBytes());
            } catch (IOException e) {}
          return questionImageRepository.save(image);
        })
        .collect(Collectors.toList());
    return saveImages.stream().map(m->m.getImg()).collect(Collectors.toList());
  }
    
  //질문글 이미지 수정
  public List<byte[]> updateImages (List <MultipartFile> newImages,long questionId) {
    Question question = questionService.findQuestion(questionId);
    List<QuestionImage> existingImage = getQuestionImage(questionId);
    
    if (existingImage != null) {
      questionImageRepository.deleteAll(existingImage);
    }
    
    return saveImages(newImages, question);
    
  }
  
  //질문글 이미지 조회
  public List<QuestionImage> getQuestionImage (Long questionId){
    List<QuestionImage> findImages = questionImageRepository.findQuestionImagesByQuestion_QuestionId(questionId);
    return findImages;
  }
}