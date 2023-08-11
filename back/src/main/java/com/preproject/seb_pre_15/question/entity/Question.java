package com.preproject.seb_pre_15.question.entity;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, length = 500)
    private String body;
    
    @Column(nullable = false)
    private Long view = (long) 0.00;
    
//    @Column
//    private String images;
//
//    @Column
//    private Long vote;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();
    
//    @OneToMany(mappedBy = "questionComment")
//    private List<questionComment> questionComments;
    
    //더미 생성용 생성자
    public Question(String title, String body, Member member) {
        this.title = title;
        this.body = body;
        this.member = member;
    }
    
    public Question() {
    
    }
}
