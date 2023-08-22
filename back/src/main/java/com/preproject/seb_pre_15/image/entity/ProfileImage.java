package com.preproject.seb_pre_15.image.entity;

import com.preproject.seb_pre_15.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProfileImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long imageId;
  
  @Lob
  private byte[] img;
  
  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;

}
