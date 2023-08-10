package com.preproject.seb_pre_15.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name = "John"; // 추후 랜덤 생성 구현

    @Column(nullable = false)
    private String email;

    @ElementCollection
    private List<String> roles;

//    @OneToMany(mappedBy = "member")
//    private List<Question> questions = new ArrayList<>();

//    @OneToMany
//    private List<AnswerEntity> answers = new ArrayList<>();

//    @Column
//    private String img;

}
