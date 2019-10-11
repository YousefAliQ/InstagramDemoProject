package com.mpp.instagram.user.controller;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.datastax.driver.core.LocalDate;
import java.util.*;

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
    @RequestMapping(method = RequestMethod.POST , value = "/signup", consumes ="application/json" ,produces = "application/json")
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

    @RequestMapping(method = RequestMethod.POST , value = "/signin",consumes = "application/json" ,produces = "application/json")
    public Map<String, String> signInUser(@RequestBody Map<String ,?> input)
    {
        Map<String, String> result = new HashMap<>();
        try {
            UserEntity isValid = userService.isUserValid((String) input.get("username"), (String) input.get("password"));
            if (isValid != null) {

                String generatedToken = UUID.randomUUID().toString();
                UserEntity entity = saveToken(isValid, generatedToken);
                if ( entity != null){
                    result.put("Result", "Success");
                    result.put("token",generatedToken);
                }

            } else {
                result.put("Result", "Fail");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }

    private UserEntity saveToken(UserEntity input, String generatedToken) {

        LocalDate.fromMillisSinceEpoch(new Date().getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, +1);
        //input.setTimestamp(calendar.getTime());
        //input.setToken_timestamp(LocalDate.fromMillisSinceEpoch(new Date().getTime()));
        input.setToken_timestamp("sfd");
        input.setToken(generatedToken);
        return userService.saveToken(input);

    }


    @RequestMapping(method = RequestMethod.POST , value = "/checkToken",consumes = "application/json" ,produces = "application/json")
    public Map<String, String> checkToken(@RequestBody Map<String ,?> input)
    {
        Map<String, String> result = new HashMap<>();
        try {
            UserEntity isActive = userService.isUserActive((String) input.get("token"));

//            if (isActive.getTimestamp().before(new Date())) { // more than an hour
//                result.put("Result", "Fail");
//            } else {
//                result.put("Result", "Success");
//            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }finally {
            result.put("Result", "Unknown Error!");
            return result;
        }

    }


}

