package com.preproject.seb_pre_15.image.entity;

import com.preproject.seb_pre_15.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QuestionImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long imageId;
  
  @Lob
  private byte[] img;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;
}
