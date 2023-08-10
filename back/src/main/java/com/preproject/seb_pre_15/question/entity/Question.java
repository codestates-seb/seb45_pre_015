package com.preproject.seb_pre_15.question.entity;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, length = 500)
    private String body;
    
    @Column(nullable = false)
    private Long view;
    
//    @Column
//    private String images;
//
//    @Column
//    private Long vote;
    
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    
    @OneToMany(mappedBy = "answer")
    private List<Answer> answers;
    
//    @OneToMany(mappedBy = "questionComment")
//    private List<questionComment> questionComments;
}
