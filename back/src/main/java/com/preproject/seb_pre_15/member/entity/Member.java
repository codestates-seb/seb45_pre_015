package com.preproject.seb_pre_15.member.entity;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name = "USER"; // 추후 랜덤 생성 구현

    @Column(nullable = false)
    private String email;

    @ElementCollection
    private List<String> roles;

    @OneToMany(mappedBy = "member",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AnswerComment> answerComments = new ArrayList<>();

    public void addAnswerComment(AnswerComment answerComment){
        if (answerComment.getMember() != this) answerComment.setMember(this);
        answerComments.add(answerComment);
    }

//    @OneToMany
//    private List<Answer> answers = new ArrayList<>();

//    @Column
//    private String img;
    
    //더미 생성용 생성자
    public Member(String email) {
        this.email = email;
    }
    
    public Member() {
    
    }
}
