package com.preproject.seb_pre_15.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ID_DOESNT_MATCH(403, "Id doesnt Match"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    NO_MORE_LIKE(403, "Like only once");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
