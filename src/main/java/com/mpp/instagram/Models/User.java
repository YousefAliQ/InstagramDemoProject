package com.mpp.instagram.Models;

import com.datastax.driver.core.LocalDate;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    //@ApiModelProperty(notes = "User Id as a Unique parameter")
    // @Column("userid")
    private int id;    //Random ID
    //@ApiModelProperty(notes = "User name as the Primary key")
    //@Column("user_name")
    //@PrimaryKey
    private String username;  //Storing UserName
    //@ApiModelProperty(notes = "User full name")
    //@Column("full_name")
    private String fullname;  //Storing fullname
    //@ApiModelProperty(notes = "User Email")
    //@Column("email")
    private String email;     //Storing Email
    //@ApiModelProperty(notes = "User Email")
    //@Column("password")
    private String password;  // Storing password

    private Profile profile;

    public User(int id, String username, String fullname, String email, String password, Profile profile) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getFullname() { return fullname; }

    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Profile getProfile() { return profile; }

    public void setProfile(Profile profile) { this.profile = profile; }

    public User(String username) {
        this.username = username;
    }
    public User() {
    }
}
