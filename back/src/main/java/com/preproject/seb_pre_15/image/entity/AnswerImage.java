package com.preproject.seb_pre_15.image.entity;

import com.preproject.seb_pre_15.answer.entity.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AnswerImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long imageId;
  
  @Lob
  private byte[] img;
  
  @ManyToOne
  @JoinColumn(name = "answer_id")
  private Answer answer;
}
