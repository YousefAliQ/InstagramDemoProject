package com.mpp.instagram.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

//import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

//@Entity
@Table("INSTAGRAM_USER")
public class UserEntity implements Serializable {
   // @Column("userid")
    private Long id;    //Random ID
    @Column("user_name")
    @PrimaryKey
    private String username;  //Storing UserName
    @Column("full_name")
    private String fullname;  //Storing fullname
    @Column("email")
    private String email;     //Storing Email
    @Column("password")
    private String password;  // Storing password

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public UserEntity(String username, String fullname, String email, String password) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

