package com.mpp.instagram.comments.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class commentsEntity {
    @Column("comment_id")
    @PrimaryKey()
    private long commentId;
    @Column("post_id")
    private long postId;
    @Column("comment")
    private String comment;

    public commentsEntity(long commentId, long postId, String comment) {
        this.commentId = commentId;
        this.postId = postId;
        this.comment = comment;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
