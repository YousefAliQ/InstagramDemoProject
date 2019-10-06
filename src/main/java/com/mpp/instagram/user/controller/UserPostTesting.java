package com.mpp.instagram.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testPost")
public class UserPostTesting {
    @RequestMapping(value="/testPost", method = RequestMethod.POST)
    public String test(@RequestParam("userName") String userName, @RequestParam("password") String password){
       if (userName.equals("raza") && password.equals("raza") ){
           return "It's Working dude!";
       }
       return "incorrect, try again!";
    }

}
