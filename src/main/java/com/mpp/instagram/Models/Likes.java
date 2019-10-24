package com.mpp.instagram.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Likes {

    private int likeId;
    private int postId;
    private String username;
    private  LocalDateTime date;

    public Likes(int likeId, int postId, String username, LocalDateTime date) {
        this.likeId = likeId;
        this.postId = postId;
        this.username = username;
        this.date  = date;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
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

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }
}
