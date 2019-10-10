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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//OverWrite Master Code ... Accepted and merged with master by Yousef.
//<<<<<<< HEAD
    //Coment addedd to check from Github Edited by joe! First time
//=======
//    //Coment addedd to check from Github Edited by joe! Second
//>>>>>>> 899e7e712737ef48b28b56d7cdb57fd20f1dda1e
    @CrossOrigin(origins  = "*")
    @RequestMapping(method = RequestMethod.POST , value = "/signin", produces="application/json")
    @ResponseBody
    //public ResponseEntity<UserEntity> signInUser(@RequestBody UserEntity input)
    public String signInUser(@RequestBody Map<String , ?> input)
    {
        //UserEntity isValid=userService.isUserValid(input.getUsername(),input.getPassword());
//        if(isValid==null)
//        {
//            return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<UserEntity>(HttpStatus.OK);
        Map<String , String> result = new HashMap<>();
        result.put("pass" , (String)input.get("password"));
        result.put("user" , (String)input.get("username"));

        return "{'error':'Username is not correct!'}";//new ResponseEntity<>( result, HttpStatus.OK );
    }
}

