package com.mpp.instagram.post.entity;


import java.time.LocalDateTime;

public class PostReturnData {

    private long post_id;

    private String postUrl;

    private LocalDateTime uploadDate;

    public PostReturnData(long post_id, String postUrl, LocalDateTime uploadDate) {
        this.post_id = post_id;
        this.postUrl = postUrl;
        this.uploadDate = uploadDate;
    }

    public PostReturnData() {

    }


    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
