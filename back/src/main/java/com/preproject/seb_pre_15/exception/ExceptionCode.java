package com.preproject.seb_pre_15.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ID_DOESNT_MATCH(403, "Id doesnt Match"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_VOTE(403, "Invalid vote operation detected"),

    COMMENT_NOT_EXIST(404, "Comment doesnt exist"),
    IMAGE_NOT_FOUND(404, "Image not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
