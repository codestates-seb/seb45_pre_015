package com.preproject.seb_pre_15.image.service;

import com.preproject.seb_pre_15.image.entity.ProfileImage;
import com.preproject.seb_pre_15.image.repository.ProfileImageRepository;
import com.preproject.seb_pre_15.image.repository.QuestionImageRepository;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileImageService {
  
  private final QuestionImageRepository questionImageRepository;
  private final ProfileImageRepository profileImageRepository;
  private final MemberService memberService;
  
  //프로필용
  public void saveImage(MultipartFile images, Long memberId) throws IOException {
    Member member = memberService.findMember(memberId);
    ProfileImage sample = new ProfileImage();
    sample.setImg(images.getBytes());
    sample.setMember(member);
    profileImageRepository.save(sample);
  }
  
  //프로필 이미지 조회
  public byte[] getImage(Long id) {
    Optional<ProfileImage> optionalImage = profileImageRepository.findById(id);
    return Objects.requireNonNull(optionalImage.orElse(null)).getImg();
  }
  
  //프로필 이미지 삭제
  public void deleteImage(Long imageId, Long memberId) {
    Optional<ProfileImage> optionalImage = profileImageRepository.findById(imageId);
    Long findImageMemberId = profileImageRepository.findById(imageId)
        .map(ProfileImage::getImageId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Image not found"));
    
    if (!findImageMemberId.equals(memberId)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to delete this image");
    }
    
    questionImageRepository.deleteById(imageId);
  }
  
  //프로필 이미지 수정
  public void updateprofileImage(MultipartFile file, Long imageId, Long memberId) throws IOException {
    ProfileImage image = profileImageRepository.findById(imageId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
    
    if (!image.getImageId().equals(memberId)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to update this image");
    }
    
    image.setImg(file.getBytes());
    profileImageRepository.save(image);
  }
}
