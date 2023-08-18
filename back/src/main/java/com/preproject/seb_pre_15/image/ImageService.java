package com.preproject.seb_pre_15.image;

import com.preproject.seb_pre_15.exception.ExceptionCode;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
  
  private final ImageRepository imageRepository;
  private final MemberService memberService;
  
  public void saveImage(MultipartFile file, Long memberId) throws IOException {
    Member member = memberService.findMember(memberId);
    Image sample = new Image();
    sample.setImg(file.getBytes());
    sample.setMember(member);
    imageRepository.save(sample);
  }
  
  public byte[] getImage(Long id) {
    Optional<Image> optionalImage =imageRepository.findById(id);
    return optionalImage.map(Image::getImg).orElse(null);
  }
  
  public void deleteImage(Long imageId, Long memberId) {
    Optional<Image> optionalImage = imageRepository.findById(imageId);
    Long findImageMemberId = optionalImage.get().getImageId();
    if(findImageMemberId.equals(memberId)){
      imageRepository.deleteById(imageId);
    }else {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to delete this image");
    }

  }

  public void updateImage(MultipartFile file, Long imageId, Long memberId) throws IOException {
    Optional<Image> optionalImage = imageRepository.findById(imageId);
    Long findImageMemberId = optionalImage.get().getImageId();
    if(findImageMemberId.equals(memberId)){
      Image image = optionalImage.get();
      image.setImg(file.getBytes());
      imageRepository.save(image);
    }else{
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to update this image");
    }
  }
}