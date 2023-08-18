package com.preproject.seb_pre_15.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

  private final ImageService imageService;
  
  @PostMapping
  public ResponseEntity<String> postImage(@RequestParam("file") MultipartFile file) {
    try {
      imageService.saveImage(file);
      return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully");
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
    }
  }
  
  @GetMapping("/{image-id}")
  public ResponseEntity<byte[]> getImage(@PathVariable("image-id") @Positive Long imageId) {
    byte[] imageData = imageService.getImage(imageId);
    return new ResponseEntity<>(imageData, HttpStatus.OK);
  }
  
  @DeleteMapping("/{image-id}")
  public ResponseEntity deleteImage(@PathVariable("image-id") @Positive Long imageId){
    imageService.deleteImage(imageId);
    return new ResponseEntity<>("success delete image", HttpStatus.NO_CONTENT);//
  }
}
