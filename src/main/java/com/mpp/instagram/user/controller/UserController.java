package com.mpp.instagram.user.controller;

import com.datastax.driver.core.LocalDate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.awt.*;
import java.util.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    // New Get Code Functionality
    @RequestMapping("/signup")
    public List<UserEntity>  getRequestFromSignUp( )
    {
        List<UserEntity> getAllData=userService.listAll(); //Getting all the data from the database
        return getAllData;
    }
    @RequestMapping("/signin")
    public String  getRequestFromSignIn()
    {
        return "SignIn Page Hit";
    }

    /// Post Requests for SignIn and SignUp
    @RequestMapping(method = RequestMethod.POST , value = "/signup", consumes ="application/json" ,produces = "application/json")
    @ApiOperation(value = "For Signing Up the User",
    notes = "Look up for the username. If it already exists then Send back as a failure otherwise insert the user record in database",
    response = Json.class)
    public Map<String, String> signUpUser(@RequestBody Map<String ,?> input)
    {
        Map<String, String> result = new HashMap<>();
        try {
            //LookUp Username In Database if it already exists
            UserEntity userexist = userService.findByUsername((String) input.get("username"));
            if (userexist == null) {
                userService.saveDataInToDatabase(input);
                result.put("Result", "Success");
                result.put("userexist", "false");
            } else {
                result.put("Result", "Fail");
                result.put("userexist", "true");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.POST , value = "/signin",consumes = "application/json" ,produces = "application/json" )
    public Map<String, String> signInUser(@RequestBody Map<String ,?> input)
    {
        Map<String, String> result = new HashMap<>();
        try {
            HashMap<String,String> map;
            map = (HashMap<String, String>) input.get("login");
            UserEntity isValid = userService.isUserValid((String) map.get("username"), (String) map.get("password"));
            if (isValid != null) {
                    result.put("Result", "Success");
                }
                else {
                    result.put("Result", "Fail");
               // result.put("Result", "Success");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
}

