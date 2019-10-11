package com.mpp.instagram.user.controller;

import com.datastax.driver.core.LocalDate;
import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;
import java.util.*;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    // New Get Code Functionality
    @RequestMapping("/signup")
    public List<UserEntity> getRequestFromSignUp() {
        List<UserEntity> getAllData = userService.listAll(); //Getting all the data from the database
        return getAllData;
    }

    @RequestMapping("/signin")
    public String getRequestFromSignIn() {
        return "SignIn Page Hit";
    }

    /// Post Requests for SignIn and SignUp
    @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For Signing Up the User",
            notes = "Look up for the username. If it already exists then Send back as a failure otherwise insert the user record in database",
            response = Json.class)
    public Map<String, String> signUpUser(@RequestBody Map<String, ?> input) {
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signin", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For Signing In the User",
            notes = "Look up for the username and password. If both matches the database then establish a session and return a token otherwise return failure. ",
            response = Json.class)
    public Map<String, String> signInUser(@RequestBody Map<String, ?> input) {
        Map<String, String> result = new HashMap<>();
        try {
            HashMap<String, String> map;
            map = (HashMap<String, String>) input.get("login");
            UserEntity isValid = userService.isUserValid((String) map.get("username"), (String) map.get("password"));
            if (isValid != null) {

                UUID generatedToken = UUID.randomUUID();
                UserEntity entity = saveToken(isValid, generatedToken);
                if (entity != null) {
                    result.put("Result", "Success");
                    result.put("Token", generatedToken.toString());
                } else {
                    result.put("Result", "Fail");
                }
            } else {
                result.put("Result", "Fail");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private UserEntity saveToken(UserEntity input, UUID generatedToken) {

        Date date = new Date();

        GregorianCalendar genTimestamp = new GregorianCalendar();
        genTimestamp.setTimeInMillis(new Date().getTime());
        genTimestamp.add(Calendar.HOUR, 1);

        date.setTime(genTimestamp.getTimeInMillis());
        input.setToken_timestamp(LocalDate.fromMillisSinceEpoch(date.getTime()));

        input.setToken(generatedToken);
        return userService.saveToken(input);

    }


    @RequestMapping(method = RequestMethod.POST, value = "/checkToken", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For authentication the Users' sessions",
            notes = "Look up for the token. If it matches the database then returns valid otherwise returns invalid. ",
            response = Json.class)

    public Map<String, String> checkToken(@RequestBody Map<String, ?> input) {
        Map<String, String> result = new HashMap<>();
        try {

            UserEntity isActive = userService.isUserActive(UUID.fromString(input.get("token").toString()));
            LocalDate localDate = LocalDate.fromMillisSinceEpoch(isActive.getToken_timestamp().getMillisSinceEpoch());

            GregorianCalendar timeStamp = new GregorianCalendar();
            timeStamp.setTimeInMillis(isActive.getToken_timestamp().getMillisSinceEpoch());

            if (!timeStamp.before(new Date())) { // token expire after an hour if the user is not active.
                result.put("Result", "valid");
            } else {
                result.put("Result", "invalid");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;


    }


}

