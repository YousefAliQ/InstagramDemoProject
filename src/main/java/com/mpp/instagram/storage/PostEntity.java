package com.mpp.instagram.storage;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Instagram_Posts")
public class PostEntity {
    @Column("post_id")
    @PrimaryKey
    private long post_id;
    @Column("post_url")
    private String postUrl;
    @Column("upload_date")
    private LocalDateTime uploadDate;
    @Column("post_description")
    private String description;
    @Column("user_name")
    private String username;
//    @Column("likes_Count")
//    private int likeCount;
//    @Column("post_comments")
//    private Set<commentsEntity> comments = new HashSet<>();

    public PostEntity(long post_id, String postUrl, LocalDateTime uploadDate, String description, String username) {
        this.post_id = post_id;
        this.postUrl = postUrl;
        this.uploadDate = uploadDate;
        this.description = description;
        this.username = username;
//        this.likeCount = likeCount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
//
//    public int getLikeCount() { return likeCount; }
//
//    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }
//
//    public Set<commentsEntity> getComments() { return comments; }
//
//    public void setComments(Set<commentsEntity> comments) { this.comments = comments; }

    @Override
    public String toString() {
        return "PostEntity{" +
                "post_id=" + post_id +
                ", postUrl='" + postUrl + '\'' +
                ", uploadDate=" + uploadDate +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}