package com.mpp.instagram.user.controller;

import com.datastax.driver.core.LocalDate;
import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.spring.web.json.Json;

import java.util.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    public UserController() {
        userService = new UserService();
    }

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

        HashMap<String, String> map;
        map = (HashMap<String, String>) input.get("signup");

        Map<String, String> result = new HashMap<>();
        try {
            //LookUp Username In Database if it already exists
            UserEntity usernaemExist = userService.findByUsername((String) map.get("username"));
            UserEntity emailExist = userService.findByEmail((String) map.get("email"));
            if (usernaemExist == null && emailExist == null) {
                userService.saveDataInToDatabase(map);
                result.put("Result", "Success");
                result.put("Massege", "Cool, try to login now!");
                //result.put("token","asdfsdf");
            } else {
                result.put("Result", "Fail");
                result.put("Massege", "Username or Email is already taken!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    @ExceptionHandler(Throwable.class)
    @RequestMapping(method = RequestMethod.POST, value = "/signin", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For Signing In the User",
            notes = "Look up for the username and password. If both matches the database then establish a session and return a token otherwise return failure. ",
            response = Json.class)
    public Map<String, String> signInUser(@RequestBody Map<String, ?> input) {
        Map<String, String> result = new HashMap<>();
        try {
            Map<String, String> map = new HashMap<>();
            map = (Map<String, String>) input.get("login");
            UserEntity isValid = userService.isUserValid((String) input.get("username"), (String) input.get("password"));
            if (isValid != null) {
                UUID generatedToken = UUID.randomUUID();
                UserEntity entity = saveToken(isValid, generatedToken);
                if (entity != null) {
                    result.put("Result", "success");
                    result.put("username", entity.getUsername());
                    result.put("fullname", entity.getFullname());
                    result.put("token", generatedToken.toString());
                    result.put("email", entity.getEmail());
                    result.put("timestamp", entity.getToken_timestamp().toString());
                } else {

                    // 500 - SERVER ERROR
                    //result.put("status","201");
                    //result.put("statusText", "Sorry dude, Unknown error. Try Again!");
                    //throw new UserNotFoundException("id-" + id);
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            } else {
                throw new UserNotFoundException();

                // 401 - UNAUTHORIZED
               /* result.put("status","201");
                result.put("statusText", "Sorry dude, invalid username or password!");*/
            }
        } catch (Exception exc) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sorry dude, invalid username or password!", exc);
//            Map<String, String> map = new HashMap<>();
//            map.put("response", new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sorry dude, invalid username or password!", exc).toString());
//            return map;

        }
        return result;
    }

    public UserEntity saveToken(UserEntity input, UUID generatedToken) {

        Date date = new Date();

        GregorianCalendar genTimestamp = new GregorianCalendar();
        genTimestamp.setTimeInMillis(new Date().getTime());
        genTimestamp.add(Calendar.HOUR, 1);

        date.setTime(genTimestamp.getTimeInMillis());
        input.setToken_timestamp(LocalDate.fromMillisSinceEpoch(date.getTime()));

        input.setToken(generatedToken);
        return userService.saveToken(input);

    }
//    @RequestMapping(method = RequestMethod.POST, value = "/checkToken", consumes = "application/json", produces = "application/json")
//    @ApiOperation(value = "For authentication the Users' sessions",
//            notes = "Look up for the token. If it matches the database then returns valid otherwise returns invalid. ",
//            response = Json.class)

    public Map<String, String> checkToken(String input) {
        Map<String, String> result = new HashMap<>();
        try {
            UserEntity isActive = userService.isUserActive(UUID.fromString(input));
            LocalDate localDate = LocalDate.fromMillisSinceEpoch(isActive.getToken_timestamp().getMillisSinceEpoch());

            GregorianCalendar timeStamp = new GregorianCalendar();
            timeStamp.setTimeInMillis(isActive.getToken_timestamp().getMillisSinceEpoch());

            if (!timeStamp.before(new Date())) { // token expire after an hour if the user is not active.
                result.put("Result", "valid");
                result.put("username", isActive.getUsername());

            } else {
                result.put("Result", "invalid");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }


}

