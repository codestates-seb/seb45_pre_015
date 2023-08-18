package com.preproject.seb_pre_15.image;

import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image extends Auditable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long imageId;
  
  @Lob
  private byte[] img;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;
}
