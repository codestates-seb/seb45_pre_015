package com.preproject.seb_pre_15.comment.questionComment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionCommentDto {

    @Getter
    @Setter
    public static class Post{
        private String body;
    }

    @Getter
    @Setter
    public static class Patch{
        private String body;
    }

    @Getter
    @Setter
    public static class Response{
        private long questionCommentId;
        private String memberEmail;
        private String body;
    }
}
