package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    @JsonProperty("comment_id")
    private int commentId;
    @JsonProperty("post_id")
    private int postId;
    private String comment;

    public Comment(int commentId, int postId, String comment) {
        this.commentId = commentId;
        this.postId = postId;
        this.comment = comment;
    }

    public Comment() {

    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
