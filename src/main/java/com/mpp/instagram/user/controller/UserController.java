package com.mpp.instagram.user.controller;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import javafx.application.Application;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    // New Get Code Functionality
    @RequestMapping("/signup")
    public List<UserEntity>  getRequestFromSignIn( )
    {
        List<UserEntity> getAllData=userService.listAll(); //Getting all the data from the database
        return getAllData;
    }
    @RequestMapping("/signin")
    public String  getRequestFromSignUp()
    {
        return "SignUp Page Hit";
    }

    /// Post Requests for SignIn and SignUp
    @RequestMapping(method = RequestMethod.POST , value = "/signup", consumes ="application/json" )
    public String signUpUser(@RequestBody UserEntity ent)
    {
        //LookUp Username In Database if it already exists
        UserEntity userexist=userService.findByUsername(ent.getUsername());
        if(userexist==null)
        {
            userService.saveDataInToDatabase(ent);
            return "success";
        }
        else {
            return "userexist";
        }
    }

    @RequestMapping(method = RequestMethod.POST , value = "/signin")
    public String signInUser(@RequestBody UserEntity input)
    {
        UserEntity isValid=userService.isUserValid(input.getUsername(),input.getPassword());
        
        if(isValid!=null)
        {
            return "success";
        }
        else
        {
            return "fail";
        }
    }
}

