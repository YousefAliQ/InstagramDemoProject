package com.mpp.instagram.user.controller;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import javafx.application.Application;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public UserEntity login(@RequestParam("userName") String userName, @RequestParam("password") String password){
//        System.out.println("userName: "+userName);
//        return userService.login(userName, password);
//    }

    // New Get Code Functionality
    @RequestMapping("/signup")
    public ResponseEntity<UserEntity>  getRequestFromSignIn( )
    {
        return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping("/signin")
    public String  getRequestFromSignUp( )
    {
        return "SignUp Page Hit";
    }

    /// Post Requests for SignIn and SignUp
    @RequestMapping(method = RequestMethod.POST , value = "/signup", produces = "application/json", consumes ="application/json" )
    public ResponseEntity<UserEntity> signUpUser(@RequestBody UserEntity ent)
    {
        UserEntity returnuser=userService.saveDataInToDatabase(ent);
        return new ResponseEntity<UserEntity>(returnuser,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST , value = "/signin")
    public ResponseEntity<UserEntity> signInUser(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        UserEntity isValid=userService.isUserValid(username,password);

//        if(isValid==false)
//        {
//            return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<UserEntity>(HttpStatus.OK);
    }



}

