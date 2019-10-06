package com.mpp.instagram.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INSTAGRAM_USER")
public class UserEntity {

    private long id;
    private String username;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
