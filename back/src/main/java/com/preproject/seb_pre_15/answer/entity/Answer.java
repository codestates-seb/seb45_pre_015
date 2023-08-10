package com.preproject.seb_pre_15.answer.entity;

import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Answer extends Auditable {
    @Id
    private Long answerId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String body;

    @Column(nullable = false)
    private Long view;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column
    private Long vote;

    //    @Column
//    private String images;

}
