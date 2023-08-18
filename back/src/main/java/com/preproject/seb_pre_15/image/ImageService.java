package com.preproject.seb_pre_15.image;

import com.preproject.seb_pre_15.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
  
  @Autowired
  private ImageRepository imageRepository;
  
  public void saveImage(MultipartFile file) throws IOException {
    Image sample = new Image();
    sample.setImg(file.getBytes());
    imageRepository.save(sample);
  }
  
  public byte[] getImage(Long id) {
    Optional<Image> optionalImage =imageRepository.findById(id);
    return optionalImage.map(Image::getImg).orElse(null);
  }
  
  public void deleteImage(Long imageId) {
    imageRepository.deleteById(imageId);
  }

  public void updateImage(MultipartFile file, Long imageId) throws IOException {
    Optional<Image> optionalImage = imageRepository.findById(imageId);
    if(optionalImage.isPresent()){
      Image image = optionalImage.get();
      image.setImg(file.getBytes());
      imageRepository.save(image);
    }else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionCode.IMAGE_NOT_FOUND.getMessage());
    }

  }
}