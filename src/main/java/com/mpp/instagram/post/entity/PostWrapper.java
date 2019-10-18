package com.mpp.instagram.post.entity;

import org.springframework.web.multipart.MultipartFile;

public class PostWrapper {

    public PostWrapper(MultipartFile image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    private MultipartFile image;
    private String title;
    private String description;

    public PostWrapper() {

    }


    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "PostWrapper{" +
                "image=" + image +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
