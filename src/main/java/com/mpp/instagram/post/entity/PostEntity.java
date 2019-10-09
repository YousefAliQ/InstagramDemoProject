package com.mpp.instagram.post.entity;

import com.mpp.instagram.comments.entity.CommentsEntity;
import com.mpp.instagram.likes.entity.LikesEntity;
import com.mpp.instagram.user.entity.UserEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;

import java.util.List;

@Table("User_Posts")
public class PostEntity {

    @Id
    private long id;
    private UserEntity user;
    private Timestamp uploadDate;
    private String multimediaUrl;
    private String Description;

    private List<LikesEntity> likes;
    private List<CommentsEntity> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getMultimediaUrl() {
        return multimediaUrl;
    }

    public void setMultimediaUrl(String multimediaUrl) {
        this.multimediaUrl = multimediaUrl;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<LikesEntity> getLikes() {
        return likes;
    }

    public void setLikes(List<LikesEntity> likes) {
        this.likes = likes;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

}
