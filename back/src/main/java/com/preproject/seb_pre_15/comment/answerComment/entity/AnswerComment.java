package com.preproject.seb_pre_15.comment.answerComment.entity;

import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class AnswerComment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerCommentId;

    @NotNull
    @Column(length = 500)
    private String body;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;
}
