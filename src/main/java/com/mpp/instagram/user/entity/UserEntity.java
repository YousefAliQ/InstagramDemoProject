package com.mpp.instagram.user.entity;

import org.springframework.data.cassandra.core.mapping.Table;

@Table("INSTAGRAM_USER")
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
