package com.preproject.seb_pre_15.question.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    @PostMapping
    public ResponseEntity createQuestion(){

        return ResponseEntity.ok();
    }

}
