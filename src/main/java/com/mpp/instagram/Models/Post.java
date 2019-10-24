package com.mpp.instagram.Models;

import java.time.LocalDateTime;
import java.util.List;

public class Post {

//    @Column("post_id")
//    @PrimaryKey
    private int post_id;
//    @Column("post_url")
    private String postUrl;
//    @Column("upload_date")
    private LocalDateTime uploadDate;
//    @Column("post_description")
    private String description;
//    @Column("user_name")
    private String username;
//    @Column("likes_Count")
    private List<Likes> likes;
//    @Column("post_comments")
    private List<Comments> comments;

    public Post(int post_id, String postUrl, LocalDateTime uploadDate, String description, String username, List<Likes> likes, List<Comments> comments) {
        this.post_id = post_id;
        this.postUrl = postUrl;
        this.uploadDate = uploadDate;
        this.description = description;
        this.username = username;
        this.likes = likes;
        this.comments = comments;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public List<Likes> getLikes() { return likes; }

    public void setLikes(List<Likes> likes) { this.likes = likes; }

    public List<Comments> getComments() { return comments; }

    public void setComments(List<Comments> comments) { this.comments = comments; }

    //    @Override
//    public String toString() {
//        return "Post{" +
//                "post_id=" + post_id +
//                ", postUrl='" + postUrl + '\'' +
//                ", uploadDate=" + uploadDate +
//                ", description='" + description + '\'' +
//                ", username='" + username + '\'' +
//                '}';
//    }
}
