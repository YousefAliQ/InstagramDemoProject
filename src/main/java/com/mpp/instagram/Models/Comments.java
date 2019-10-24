package com.mpp.instagram.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Comments {

    private int commentId;
    private int postId;
    private String username;
    private String comment;
    private LocalDateTime date;

    public Comments(int commentId, int postId, String username, String comment, LocalDateTime date) {
        this.commentId = commentId;
        this.postId = postId;
        this.username = username;
        this.comment = comment;
        this.date = date;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCommentDate() { return date; }

    public void setCommentDate(LocalDateTime date) { this.date = date; }

}
