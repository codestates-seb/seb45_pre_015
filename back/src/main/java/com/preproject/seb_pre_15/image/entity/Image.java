//package com.preproject.seb_pre_15.image.entity;
//
//import com.preproject.seb_pre_15.answer.entity.Answer;
//import com.preproject.seb_pre_15.audit.Auditable;
//import com.preproject.seb_pre_15.member.entity.Member;
//import com.preproject.seb_pre_15.question.entity.Question;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//public class Image extends Auditable {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long imageId;
//
//  @Lob
//  private byte[] img;
//
//  @ManyToOne
//  @JoinColumn(name = "member_id")
//  private Member member;
//
//  @ManyToOne
//  @JoinColumn(name = "question_id")
//  private Question question;
//
//  @ManyToOne
//  @JoinColumn(name = "answer_id")
//  private Answer answer;
//}
