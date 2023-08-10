package com.preproject.seb_pre_15.member.entity;

import com.preproject.seb_pre_15.audit.Auditable;
import com.preproject.seb_pre_15.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

//    @OneToMany
//    private List<Answer> answers = new ArrayList<>();

//    @Column
//    private String img;

}
