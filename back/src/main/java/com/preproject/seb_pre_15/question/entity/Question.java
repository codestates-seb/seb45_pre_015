package com.preproject.seb_pre_15.question.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Question {
    @Id
    private Long questionId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 500)
    private String body;
    @Column(nullable = false)
    private Long view;
    @Column
    private String images;
    @Column
    private Long vote;
    @Column
    private Date createdAt;
    @Column
    private Date modifiedAt;
}
