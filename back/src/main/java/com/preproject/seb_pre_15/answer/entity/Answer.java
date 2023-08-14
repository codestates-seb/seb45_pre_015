package com.preproject.seb_pre_15.answer.entity;

import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Answer extends Auditable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false, length = 500)
    private String body;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private Long view = 0L;

    @OneToMany(mappedBy = "answer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AnswerComment> answerComments = new ArrayList<>();

    @Column
    private Long vote;

    @Column
    private String images;
    
    public Answer(String body, Member member, Question question) {
        this.body = body;
        this.member = member;
        this.question = question;
    }
    
    public Answer() {
    
    }
    
    //더미 생성용 생성자
    


    public void addAnswerComment(AnswerComment answerComment){
        if (answerComment.getAnswer() != this) answerComment.setAnswer(this);
        answerComments.add(answerComment);
    }
}
