package com.preproject.seb_pre_15.answer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    private Long answerId;
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
