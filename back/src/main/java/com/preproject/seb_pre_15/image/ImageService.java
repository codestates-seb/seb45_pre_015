package com.preproject.seb_pre_15.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
  

  private final ImageRepository imageRepository;
  
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
  }
}