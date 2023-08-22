package com.preproject.seb_pre_15.answer.entity;

import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.comment.answerComment.entity.AnswerComment;
import com.preproject.seb_pre_15.image.entity.AnswerImage;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private Long view = 0L;

    @OneToMany(mappedBy = "answer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AnswerComment> answerComments = new ArrayList<>();

    @Column
    private Long vote = 0L;
    
    @OneToMany(mappedBy = "answer",cascade = CascadeType.REMOVE)
    private List<AnswerImage> images;
    
    
    //더미 생성용 생성자
    public Answer(String body, Member member, Question question, long vote) {
        this.body = body;
        this.member = member;
        this.question = question;
        this.vote = vote;
    }
    
    public Answer() {}
    public void addAnswerComment(AnswerComment answerComment){
        if (answerComment.getAnswer() != this) answerComment.setAnswer(this);
        answerComments.add(answerComment);
    }
}
