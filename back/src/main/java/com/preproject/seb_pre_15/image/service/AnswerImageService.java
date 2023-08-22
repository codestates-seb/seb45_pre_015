package com.preproject.seb_pre_15.image.service;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.answer.service.AnswerService;
import com.preproject.seb_pre_15.image.entity.AnswerImage;
import com.preproject.seb_pre_15.image.entity.QuestionImage;
import com.preproject.seb_pre_15.image.repository.AnswerImageRepository;
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
  
  private final AnswerImageRepository answerImageRepository;
  private final AnswerService answerService;
  
  //답변글 이미지 저장
  public List<byte[]> saveImages(List<MultipartFile> images, Answer answer){
    List<AnswerImage> saveImages = images.stream()
        .filter(multipartFile -> !multipartFile.isEmpty())
        .map(multipartFile -> {
          AnswerImage image = new AnswerImage();
          try {
            image.setAnswer(answer);
            image.setImg(multipartFile.getBytes());
            } catch (IOException e) {}
          return answerImageRepository.save(image);
        })
        .collect(Collectors.toList());
    return saveImages.stream().map(m->m.getImg()).collect(Collectors.toList());
  }
    
  //답변글 이미지 수정
  public List<byte[]> updateImages (List <MultipartFile> newImages,long answerId) {
    Answer answer = answerService.findAnswer(answerId);
    List<AnswerImage> existingImage = getAnswerImage(answerId);
    
    if (existingImage != null) {
      answerImageRepository.deleteAll(existingImage);
    }
    
    return saveImages(newImages, answer);
    
  }
  
  //답변글 이미지 조회
  public List<AnswerImage> getAnswerImage (Long answerId){
    List<AnswerImage> findImages = answerImageRepository.findAnswerImagesByAnswer_AnswerId(answerId);
    return findImages;
  }
}