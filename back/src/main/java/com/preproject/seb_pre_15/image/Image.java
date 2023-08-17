package com.preproject.seb_pre_15.image;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long imageId;
  
  @Lob
  private byte[] img;
}
