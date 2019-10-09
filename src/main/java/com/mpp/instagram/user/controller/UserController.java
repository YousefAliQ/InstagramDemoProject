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
    public List<UserEntity>  getRequestFromSignIn( )
    {
        List<UserEntity> getAllData=userService.listAll(); //Getting all the data from the database
        return getAllData;
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
//OverWrite Master Code
//<<<<<<< HEAD
    //Coment addedd to check from Github Edited by joe! First time
//=======
//    //Coment addedd to check from Github Edited by joe! Second
//>>>>>>> 899e7e712737ef48b28b56d7cdb57fd20f1dda1e
    @RequestMapping(method = RequestMethod.POST , value = "/signin")
    public ResponseEntity<UserEntity> signInUser(@RequestBody UserEntity input)
    {
        UserEntity isValid=userService.isUserValid(input.getUsername(),input.getPassword());
        
        if(isValid==null)
        {
            return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserEntity>(HttpStatus.OK);
    }



}

