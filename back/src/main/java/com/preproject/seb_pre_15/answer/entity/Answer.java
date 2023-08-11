package com.preproject.seb_pre_15.answer.entity;

import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

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

//    @OneToMany(mappedBy = "answer")
//    private List<Comment> comments = new ArrayList<>();

    @Column
    private Long vote;

    @Column
    private String images;

}
