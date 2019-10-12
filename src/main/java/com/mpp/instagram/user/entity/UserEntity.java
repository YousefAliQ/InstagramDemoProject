package com.mpp.instagram.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
// import jdk.jfr.Description; only works for Java 11 : by Yousef Quran
import org.springframework.data.annotation.Id;
import com.datastax.driver.core.LocalDate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

//import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
@ApiModel(description = "Details About the User Entity")
@Table("INSTAGRAM_USER")
public class UserEntity implements Serializable {
    @ApiModelProperty(notes = "User Id as a Unique parameter")
   // @Column("userid")
    private Long id;    //Random ID
    @ApiModelProperty(notes = "User name as the Primary key")
    @Column("user_name")
    @PrimaryKey
    private String username;  //Storing UserName
    @ApiModelProperty(notes = "User full name")
    @Column("full_name")
    private String fullname;  //Storing fullname
    @ApiModelProperty(notes = "User Email")
    @Column("email")
    private String email;     //Storing Email
    @ApiModelProperty(notes = "User Email")
    @Column("password")
    private String password;  // Storing password
    @ApiModelProperty(notes = "User Token")
    @Column("userToken")
    private UUID userToken;  // Storing token
    @ApiModelProperty(notes = "Token Timestamp")
    @Column("timestamp")
    private LocalDate token_timestamp;  // Storing timestamp

    public UserEntity(String username, String fullname, String email, String password, UUID userToken, LocalDate timestamp) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.userToken = userToken;
        this.token_timestamp = timestamp;
    }

    public UUID getToken() {
        return userToken;
    }

    public void setToken(UUID token) {
        this.userToken = token;
    }

    public LocalDate getToken_timestamp() {
        return token_timestamp;
    }

    public void setToken_timestamp(LocalDate token_timestamp) {
        this.token_timestamp = token_timestamp;
    }


    public UserEntity(String username) {
        this.username = username;
    }
    public UserEntity() {
    }



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

